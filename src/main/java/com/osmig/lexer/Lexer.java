package com.osmig.lexer;

import com.osmig.parser.Parser;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.osmig.lexer.Sudo.codeScanner;
import static javax.lang.model.SourceVersion.isIdentifier;

public class Lexer {
   private List<String> lines;


    public Lexer(List<String> lines) throws IOException {
        this.lines = lines;
        tokenizeEachLine();
    }

    private static final Set<String> KEYWORDS = Set.of("PRINT", "INT", "LOOP"); // Add any other keywords here
    public Token tokenizeEachLine() {
        List<String> lines = this.lines; // Example list
        List<String> tokenList = new ArrayList<>();
        List<TokenType> tokenTypes = new ArrayList<>();

        for (String line : lines) {
            String[] pieces = line.split(" "); // Split each line by space
            Pattern pattern = Pattern.compile("\"([^\"]*)\"|\\S+");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String token = matcher.group(); // This will give us the matched token
                tokenList.add(token);
            }
            tokenList.add("\\n");
        }

        if (tokenList.getLast().equals("\\n")){
            tokenList.add("\\E");
        }

        for (String token : tokenList) {
            if (token.equals("IS")) { // Check if the token is capitalized
                tokenTypes.add(TokenType.KEYWORD); // Assign to KEYWORD
            } else if (isIdentifier(token)){
                tokenTypes.add(TokenType.IDENTIFIER);
            }else if (isDecimalNumber(token)) { // Check if token is a decimal number
                tokenTypes.add(TokenType.NUMBER);
            }else if (isStringLiteral(token)){
                tokenTypes.add(TokenType.STRING);
            }
            else if (token.equals("\\n")){
                tokenTypes.add(TokenType.NEWLINE);
            }else if (token.equals("\\E")){
                tokenTypes.add(TokenType.EOF);
            }
            else {
                tokenTypes.add(TokenType.UNKNOWN);
            }
        }

        for(int i = 0; i<tokenList.size(); i++){
            System.out.println("Token -> " + tokenList.get(i) + ": Type -> " + tokenTypes.get(i));
        }

        Parser parser = new Parser(tokenList, tokenTypes);

        return null;
    }

    private static boolean isStringLiteral(String token) {
        return token.matches("^\".*\"$");
    }

    private boolean isDecimalNumber(String token) {
        String decimalRegex = "-?\\d+\\.\\d+";
        Pattern pattern = Pattern.compile(decimalRegex);
        Matcher matcher = pattern.matcher(token);
        return matcher.matches(); // Returns true if the token matches the regex
    }

    private boolean isIdentifier(String s) {
        return s.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }



}