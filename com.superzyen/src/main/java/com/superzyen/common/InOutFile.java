package com.superzyen.common;

import java.io.File;

public class InOutFile {
    public static final String FILE_PATH = "./input";
    public static final String FILE_NAME = "dsl.txt";
    public static final String OUTPUT_PATH = "./output";

    public static File getInputFile() {
        File floder = new File(InOutFile.FILE_PATH);
        if (!floder.exists() || floder == null) {
            floder.mkdir();
        }

        return new File(InOutFile.FILE_PATH + File.separator + InOutFile.FILE_NAME);
    }

    public static File getOutputFile() {
        File outFolder = new File(InOutFile.OUTPUT_PATH);
        if (!outFolder.exists() || outFolder == null) {
            outFolder.mkdir();
        }
        return new File(InOutFile.OUTPUT_PATH + File.separator + InOutFile.FILE_NAME);
    }
}
