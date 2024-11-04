package com.osmig.lexer;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
   private List<String> lines;


    public Lexer(List<String> lines) throws IOException {
        this.lines = lines;
        tokenizeEachLine();
    }

    private static final Set<String> KEYWORDS = Set.of("PRINT", "INT", "LOOP"); // Add any other keywords here
    public List<Token> tokenizeEachLine() {
        List<String> lines = this.lines; // Example list
        List<String> tokenList = new ArrayList<>();
        List<TokenType> tokenTypes = new ArrayList<>();
        boolean unknownToken = false;

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
        int lineCount = 1;
        for (String token : tokenList) {
            if (token.equals("IS") || token.equals("PRINT")) { // Check if the token is capitalized
                tokenTypes.add(TokenType.KEYWORD); // Assign to KEYWORD
            } else if (isIdentifier(token)){
                tokenTypes.add(TokenType.IDENTIFIER);
            }else if (isDecimalNumber(token)) { // Check if token is a decimal number
                tokenTypes.add(TokenType.NUMBER);
            }else if (isStringLiteral(token)){
                tokenTypes.add(TokenType.STRING);
            }
            else if (token.equals("\\n")){
                lineCount++;
                tokenTypes.add(TokenType.NEWLINE);
            }else if (token.equals("\\E")){
                tokenTypes.add(TokenType.EOF);
            }
            else {
                // quit early
                System.out.println("unknow token: '" + token + "' or symbol, expected a: Identifier, Keyword, number, or native symbol in line " + lineCount);
                tokenTypes.add(TokenType.UNKNOWN);
                unknownToken = true;
            }
        }

        if (unknownToken == false) {
            printTokens(tokenList, tokenTypes);
            List<Token> tokens = getTokenList(tokenTypes, tokenList);
            System.out.println(tokens);
            return tokens;
        }else {
            return null;
        }

    }

    private List<Token> getTokenList(List<TokenType> tokenTypes, List<String> tokenList) {
        List<Token> tokens = new ArrayList<>();
        System.out.println("~made into token and added to list ~");
        for (int i = 0; i< tokenList.size(); i++){
            tokens.add(i, new Token(tokenTypes.get(i), tokenList.get(i)));
            // sout(tokens)
        }
        return tokens;
    }


    public static void printTokens(List<String> tokenList, List<TokenType> tokenTypes){
        System.out.println("");
        System.out.println("~Tokenized~");
        for(int i = 0; i<tokenList.size(); i++){
            System.out.println("Token -> " + tokenList.get(i) + " : Type -> " + tokenTypes.get(i));
        }
        System.out.println("");
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