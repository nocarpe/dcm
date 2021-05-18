package file;

import java.util.Properties;

public class GenericTokenParser {

    private final String openToken;

    private final String closeToken;

    private final TokenHandler handler;

    public GenericTokenParser(String openToken, String closeToken, TokenHandler handler) {
        this.openToken = openToken;
        this.closeToken = closeToken;
        this.handler = handler;

    }

    // 主要的逻辑就是取出expression，委托TokenHandler来替换expression。
    public String parse(String text) {
        if (text == null || text.isEmpty()) {
            return "";

        }

        // search open token

        int start = text.indexOf(openToken, 0);

        if (start == -1) {
            return text;

        }

        char[] src = text.toCharArray();

        int offset = 0;

        final StringBuilder builder = new StringBuilder();

        StringBuilder expression = null;

        while (start > -1) {
            if (start > 0 && src[start - 1] == '\\') {
                // this open token is escaped. remove the backslash and continue.

                builder.append(src, offset, start - offset - 1).append(openToken);

                offset = start + openToken.length();

            } else {
                // found open token. let's search close token.

                if (expression == null) {
                    expression = new StringBuilder();

                } else {
                    expression.setLength(0);

                }

                builder.append(src, offset, start - offset);

                offset = start + openToken.length();

                int end = text.indexOf(closeToken, offset);

                while (end > -1) {
                    if (end > offset && src[end - 1] == '\\') {
                        // this close token is escaped. remove the backslash and continue.

                        expression.append(src, offset, end - offset - 1).append(closeToken);

                        offset = end + closeToken.length();

                        end = text.indexOf(closeToken, offset);

                    } else {
                        expression.append(src, offset, end - offset);

                        offset = end + closeToken.length();

                        break;

                    }

                }

                if (end == -1) {
                    // close token was not found.

                    builder.append(src, start, src.length - start);

                    offset = src.length;

                } else {
                    builder.append(handler.handleToken(expression.toString()));

                    offset = end + closeToken.length();

                }

            }

            start = text.indexOf(openToken, offset);

        }

        if (offset < src.length) {
            builder.append(src, offset, src.length - offset);

        }

        return builder.toString();

    }


    public static GenericTokenParser build() {
        Properties params = new Properties();
        params.put("${agreementManName}", "张三");
        params.put("${agreementManCardNo}", "3123131231");
        params.put("${account}", "12988832189");
        params.put("${amount}", "190");
        params.put("${monthNum}", "3321");
        params.put("${amountContent}", "金额");
        params.put("${carModel}", "V5");
        params.put("${carNo}", "沪Adsa");
        params.put("${carBrand}", "宝马");
        VariableTokenHandler tokenHandler = new VariableTokenHandler(params);
        GenericTokenParser parser = new GenericTokenParser("${", "}", tokenHandler);
        return parser;
    }

}




