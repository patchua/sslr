/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.sslr.api;

public final class Trivia {

  public enum TriviaKind {
    COMMENT,
    PREPROCESSOR,
    SKIPPED_TEXT
  }

  private TriviaKind kind;

  private Token[] tokens = new Token[0];
  private PreprocessingDirective preprocessingDirective;

  private Trivia() {
  }

  public Token getToken() {
    return tokens.length == 0 ? null : tokens[0];
  }

  public Token[] getTokens() {
    return tokens;
  }

  public boolean isComment() {
    return kind == TriviaKind.COMMENT;
  }

  public boolean isPreprocessor() {
    return kind == TriviaKind.PREPROCESSOR;
  }

  public boolean isSkippedText() {
    return kind == TriviaKind.SKIPPED_TEXT;
  }

  public boolean hasPreprocessingDirective() {
    return preprocessingDirective != null;
  }

  public PreprocessingDirective getPreprocessingDirective() {
    return preprocessingDirective;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Token token : tokens) {
      sb.append(token.getOriginalValue());
      sb.append(' ');
    }

    String value = sb.toString();
    if ("".equals(value)) {
      value = " ";
    }

    return "TRIVIA kind=" + kind + " value =" + value;
  }

  private static Trivia createTrivia(TriviaKind kind, Token... tokens) {
    Trivia trivia = new Trivia();
    trivia.kind = kind;
    trivia.tokens = tokens;
    return trivia;
  }

  public static Trivia createComment(Token commentToken) {
    return createTrivia(TriviaKind.COMMENT, commentToken);
  }

  public static Trivia createSkippedText(Token... tokens) {
    return createTrivia(TriviaKind.SKIPPED_TEXT, tokens);
  }

  public static Trivia createPreprocessingToken(Token preprocessingToken) {
    return createTrivia(TriviaKind.PREPROCESSOR, preprocessingToken);
  }

  public static Trivia createPreprocessingDirective(PreprocessingDirective preprocessingDirective) {
    Trivia trivia = new Trivia();
    trivia.kind = TriviaKind.PREPROCESSOR;
    trivia.preprocessingDirective = preprocessingDirective;
    return trivia;
  }

  public static Trivia createPreprocessingDirective(AstNode ast, Grammar grammar) {
    return createPreprocessingDirective(PreprocessingDirective.create(ast, grammar));
  }

}
