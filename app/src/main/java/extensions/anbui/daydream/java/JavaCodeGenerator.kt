package extensions.anbui.daydream.java

import extensions.anbui.daydream.configs.Configs
import extensions.anbui.daydream.project.ProjectBuildConfigs

object JavaCodeGenerator {
    @JvmStatic
    fun ifLogic(condition : String, logic: String): String {
        if (logic.isEmpty() || condition == "false") return ""

        if (condition == "true") {
            return logic
        }

        return java.lang.String.format(
            if (isUseSingleLineLambda(logic)) "if (%s) %s" else "if (%s) {\r\n%s\r\n}",
            condition, logic
        )
    }

    @JvmStatic
    fun ifElseLogic(condition : String, logicIf : String, logicElse : String): String {
        if (logicIf.isEmpty() && logicElse.isEmpty()) {
            return ""
        } else if (!logicIf.isEmpty() && logicElse.isEmpty()) {
            return ifLogic(condition, logicIf)
        } else if (logicIf.isEmpty() && !logicElse.isEmpty()) {
            return ifLogic("!($condition)", logicElse)
        }

        if (condition == "true") {
            return logicIf
        } else if (condition == "false") {
            return logicElse
        }

        val isSingleLine = isUseSingleLineLambda(logicIf) && isUseSingleLineLambda(logicElse)

        return if (!logicElse.isEmpty()) {
            String.format(
                if (isSingleLine) "if (%s) %s else %s" else "if (%s) {\r\n%s\r\n} else {\r\n%s\r\n}",
                condition,
                logicIf,
                logicElse
            )
        } else {
            ifLogic(condition, logicIf)
        }
    }


    @JvmStatic
    fun setOnClickListenerEvent(componentName: String, logic: String): String {
        return if (isUseLambda(Configs.currentProjectID)) {
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
        return if (isUseLambda(Configs.currentProjectID)) {
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
        return if (isUseLambda(Configs.currentProjectID)) {
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
        return if (isUseLambda(Configs.currentProjectID)) {
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

        val isSingleLine = isUseSingleLineLambda(processedLogic)

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

        val isSingleLine = isUseSingleLineLambda(processedLogic)

        return "$componentName = $newVaribles -> " +
                (if (isSingleLine) "" else "{\r\n") +
                processedLogic +
                (if (isSingleLine) "" else "\r\n};")
    }

    @JvmStatic
    fun isUseSingleLineLambda(logic : String) : Boolean {
        val lines = logic.lines().filter { it.isNotBlank() }

        if (lines.size == 1 && !logic.isEmpty()) {
            return !logic.startsWith("if (") &&
                    !logic.startsWith("for (") &&
                    !logic.startsWith("while (") &&
                    !logic.startsWith("do ") &&
                    !logic.startsWith("try ")
        }

        return false
    }

    @JvmStatic
    fun isUseLambda(projectID : String) : Boolean {
        return !ProjectBuildConfigs.isUseJava7(projectID)
    }
}