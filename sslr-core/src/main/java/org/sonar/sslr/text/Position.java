/*
 * SonarSource Language Recognizer
 * Copyright (C) 2010 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.sslr.text;

import com.google.common.base.Objects;

import javax.annotation.Nullable;

import java.io.File;

/**
 * <p>This class is not intended to be sub-classed by clients.</p>
 *
 * @since 1.17
 */
public class Position {

  private final File file;
  private final int line;
  private final int column;

  public Position(int line, int column) {
    this(null, line, column);
  }

  public Position(@Nullable File file, int line, int column) {
    this.file = file;
    this.line = line;
    this.column = column;
  }

  @Nullable
  public File getFile() {
    return file;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Position)) {
      return false;
    }
    Position other = (Position) obj;
    return this.file == other.file
      && this.line == other.line
      && this.column == other.column;
  }

  @Override
  public int hashCode() {
    return 31 * line + column;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
        .add("file", file)
        .add("line", line)
        .add("column", column)
        .toString();
  }

}
