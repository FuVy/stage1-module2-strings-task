package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        var split = signatureString.split("\\(");

        var argsString = split[1].replace(")", "").split(", ");
        List<MethodSignature.Argument> args = new ArrayList<>();
        if (!argsString[0].equals("")) {
            for (var pair : argsString) {
                System.out.println("added");
                var pairSplit = pair.split(" ");
                args.add(new MethodSignature.Argument(pairSplit[0], pairSplit[1]));
            }
        }

        var mainPart = split[0].split(" ");
        String methodName = mainPart[mainPart.length - 1];
        String returnType = mainPart[mainPart.length - 2];
        String accessModifier = null;
        if (mainPart.length == 3) {
            accessModifier = mainPart[0];
        }

        MethodSignature signature = new MethodSignature(methodName, args);
        signature.setAccessModifier(accessModifier);
        signature.setReturnType(returnType);
        return signature;
    }
}
