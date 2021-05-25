package file;

import java.util.Properties;

public class VariableTokenHandler implements TokenHandler {

    private final Properties variables;

    private final boolean enableDefaultValue;

    private final String defaultValueSeparator;

    public VariableTokenHandler(Properties variables) {
        this.variables = variables;

        this.enableDefaultValue = false;

        this.defaultValueSeparator = ":";

    }

    private String getPropertyValue(String key, String defaultValue) {
        return (variables == null) ? defaultValue : variables.getProperty(key, defaultValue);

    }

    @Override
    public String handleToken(String content) {
        if (variables != null) {
            String key = content;

            if (enableDefaultValue) {
                final int separatorIndex = content.indexOf(defaultValueSeparator);

                String defaultValue = null;

                if (separatorIndex >= 0) {
                    key = content.substring(0, separatorIndex);

                    defaultValue = content.substring(separatorIndex + defaultValueSeparator.length());

                }

                if (defaultValue != null) {
                    return variables.getProperty(key, defaultValue);

                }

            }

            if (variables.containsKey(key)) {
                return variables.getProperty(key);

            }

        }

        return "${" + content + "}";

    }

}