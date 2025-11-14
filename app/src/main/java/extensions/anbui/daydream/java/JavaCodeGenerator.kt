package extensions.anbui.daydream.java

import extensions.anbui.daydream.configs.Configs
import extensions.anbui.daydream.project.ProjectBuildConfigs

object JavaCodeGenerator {
    @JvmStatic
    fun setOnClickListenerEvent(componentName: String, logic: String): String {
        val isUseLambda = !ProjectBuildConfigs.isUseJava7(Configs.currentProjectID)
        return if (isUseLambda) {
            processEventLogicCodeWithLambda(
                componentName,
                "setOnClickListener(_v",
                logic,
                "public void onClick"
            )
        } else {
            componentName + ".setOnClickListener(new View.OnClickListener() {\r\n" +
                    logic +
                    "\r\n});"
        }
    }

    @JvmStatic
    fun setOnLongClickListenerEvent(componentName: String, logic: String): String {
        val isUseLambda = !ProjectBuildConfigs.isUseJava7(Configs.currentProjectID)
        return if (isUseLambda) {
            processEventLogicCodeWithLambda(
                componentName,
                "setOnLongClickListener(_v",
                logic,
                "public boolean onLongClick"
            )
        } else {
            componentName + ".setOnLongClickListener(new View.OnLongClickListener() {\r\n" +
                    logic +
                    "\r\n});"
        }
    }

    @JvmStatic
    fun setOnCheckedChangedListenerEvent(componentName: String, logic: String): String {
        val isUseLambda = !ProjectBuildConfigs.isUseJava7(Configs.currentProjectID)
        return if (isUseLambda) {
            processEventLogicCodeWithLambda(
                componentName,
                "setOnCheckedChangeListener((_param1, _param2)",
                logic,
                "public void onCheckedChanged"
            )
        } else {
            componentName + ".setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {\r\n" +
                    logic +
                    "\r\n});"
        }
    }

    @JvmStatic
    fun setOnUserEarnedRewardListenerEvent(rewardedAdID: String, logic: String): String {
        val isUseLambda = !ProjectBuildConfigs.isUseJava7(Configs.currentProjectID)
        return if (isUseLambda) {
            processNewListenerEventLogicCodeWithLambda(
                "_" + rewardedAdID + "_on_user_earned_reward_listener",
                "_param1",
                "int _rewardAmount = _param1.getAmount();\r\n" +
                        "String _rewardType = _param1.getType();\r\n" +
                        logic,
                "public void onUserEarnedReward"
            )
        } else {
            "_" + rewardedAdID + "_on_user_earned_reward_listener = new OnUserEarnedRewardListener() {\r\n" +
                    "@Override\r\npublic void onUserEarnedReward(RewardItem _param1) {\r\n" +
                    "int _rewardAmount = _param1.getAmount();\r\n" +
                    "String _rewardType = _param1.getType();\r\n" +
                    logic + "\r\n" +
                    "}\r\n};"
        }
    }

    @JvmStatic
    fun processEventLogicCodeWithLambda(
        componentName: String,
        eventListener: String,
        logic: String,
        eventInside: String
    ): String {
        var processedLogic = logic

        if (!logic.isEmpty()) {
            processedLogic = processedLogic.replaceFirst(
                Regex("^\\s*@Override\\s*\\r?\\n", RegexOption.MULTILINE),
                ""
            )

            processedLogic = processedLogic.replaceFirst(
                Regex("^\\s*$eventInside\\([^)]*\\)\\s*\\{\\s*\\r?\\n", RegexOption.MULTILINE),
                ""
            )

            processedLogic = processedLogic.substringBeforeLast("\r\n}")
        }

        val lines = processedLogic.lines().filter { it.isNotBlank() }
        val isSingleLine = lines.size == 1 && !processedLogic.isEmpty()

        return "$componentName.$eventListener -> " +
                (if (isSingleLine) "" else "{\r\n") +
                (if (isSingleLine) processedLogic.substringBeforeLast(";") else processedLogic) +
                (if (isSingleLine) "" else "\r\n}") +
                ");"
    }

    @JvmStatic
    fun processNewListenerEventLogicCodeWithLambda(
        componentName: String,
        newVaribles: String,
        logic: String,
        eventInside: String
    ): String {
        var processedLogic = logic

        if (!logic.isEmpty()) {
            processedLogic = processedLogic.replaceFirst(
                Regex("^\\s*@Override\\s*\\r?\\n", RegexOption.MULTILINE),
                ""
            )

            processedLogic = processedLogic.replaceFirst(
                Regex("^\\s*$eventInside\\([^)]*\\)\\s*\\{\\s*\\r?\\n", RegexOption.MULTILINE),
                ""
            )

            processedLogic = processedLogic.substringBeforeLast("\r\n}")
        }

        val lines = processedLogic.lines().filter { it.isNotBlank() }
        val isSingleLine = lines.size == 1 && !processedLogic.isEmpty()

        return "$componentName = $newVaribles -> " +
                (if (isSingleLine) "" else "{\r\n") +
                processedLogic +
                (if (isSingleLine) "" else "\r\n};")
    }
}