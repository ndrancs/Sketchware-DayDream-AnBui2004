package extensions.anbui.daydream.java

import extensions.anbui.daydream.configs.Configs
import extensions.anbui.daydream.project.ProjectBuildConfigs

object JavaCodeGenerator {
    @JvmStatic
    fun setOnClickEvent(componentName : String, logic : String) : String {
        val isUseLambda = !ProjectBuildConfigs.isUseJava7(Configs.currentProjectID)
        var processedLogic = logic

        if (!logic.isEmpty() && isUseLambda) {
            processedLogic = processedLogic.replaceFirst(
                Regex("^\\s*@Override\\s*\\r?\\n", RegexOption.MULTILINE),
                ""
            )

            processedLogic = processedLogic.replaceFirst(
                Regex("^\\s*public void onClick\\([^)]*\\)\\s*\\{\\s*\\r?\\n", RegexOption.MULTILINE),
                ""
            )

            processedLogic = processedLogic.substringBeforeLast("\r\n}")
        }

        val lines = processedLogic.lines().filter { it.isNotBlank() }
        val isSingleLine = lines.size == 1

        return if (isUseLambda) {
            "$componentName.setOnClickListener( v -> " +
                    (if (isSingleLine) "" else "{\r\n") +
                    (if (isSingleLine) processedLogic.substringBeforeLast(";") else processedLogic) +
                    (if (isSingleLine) "" else  "\r\n}") +
                    ");"
        } else {
            componentName + ".setOnClickListener(new View.OnClickListener() {\r\n" +
                    processedLogic +
                    "\r\n});"
        }
    }
}