package org.oca.assertions;

/**
 * Assertions can be enabled or disabled for specific packages or classes.
 * To specify a class, use the class name. To specify a package,
 * use the package name followed by "..." (three dots):
 * java -ea:<class> myPackage.MyProgram
 * java -da:<package>... myPackage.MyProgram
 * Each enable or disable modifies the one before it.
 * This allows you to enable assertions in general,
 * but disable them in a package and its subpackages: java -ea -da:<package>... myPackage.myProgram
 * To enable assertion for one package and disable for other you can use:
 * java -ea:<package1>... -da:<package2>... myPackage.MyProgram
 * You can enable or disable assertions in the unnamed root (default)package (the one in the current directory)
 * using the following commands:
 * java -ea:... myPackage.myProgram java -da:... myPackage.myProgram
 * Note that when you use a package name in the ea or da flag,
 * the flag applies to that package as well as its subpackages.
 * For example, java -ea:com... -da:com.enthuware... com.enthuware.
 * Main The above command first enables assertions for all the classes in com as well as for the classes in the subpackages of com.
 * It then disables assertions for classes in package com.enthuware and its subpackages.
 * */

public class AssertTest {
}
