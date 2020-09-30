package com.superzyen.verifiedtool.offline;

import com.superzyen.common.InOutFile;
import org.eclipse.jetty.util.StringUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DateVerifiedTool {

    private Map<String, List<String>> exceptionAddressMap;

    DateVerifiedTool() {
        this.exceptionAddressMap = new HashMap<>();
    }

    public void checkValid() throws IOException {
        FileReader fileReader = new FileReader(InOutFile.getInputFile());
        BufferedReader reader = new BufferedReader(fileReader);

        List<String> addressList = null;
        while (reader.ready()) {
            String mailAddress = reader.readLine().trim();
            String key = "";
            if (StringUtil.isBlank(mailAddress)) {
                key = "Address is null";
                this.sortErrorAddress(key, addressList, mailAddress);
            }

            for (int i = 0, j = mailAddress.length(); i < j; i++) {
                char c = mailAddress.charAt(i);
                if (c != '@') {
                    if (c > ' ' && c < 127) {
                        if ("()<>,;:\\\"[]@".indexOf(c) >= 0) {
                            key = "Local address contains illegal character";
                            this.sortErrorAddress(key, addressList, mailAddress);
                            break;
                        }
                    } else {
                        key = "Local address contains control or whitespace";
                        this.sortErrorAddress(key, addressList, mailAddress);
                        break;
                    }
                }
            }
        }
    }

    private void sortErrorAddress(String key, List<String> addressList, String mailAddress) {
        if (this.exceptionAddressMap.containsKey(key)) {
            addressList = this.exceptionAddressMap.get(key);
            addressList.add(mailAddress);
        } else {
            addressList = new ArrayList<>();
            addressList.add(mailAddress);
            this.exceptionAddressMap.put(key, addressList);
        }
    }

    public String showException() {
        StringBuilder stringBuilder = null;
        if (this.exceptionAddressMap == null) {
            return "No illegal";
        } else {
            stringBuilder = new StringBuilder();
            for (String key : this.exceptionAddressMap.keySet()) {
                stringBuilder.append(key);
                List<String> addList = this.exceptionAddressMap.get(key);
                String addString = addList.stream().collect(Collectors.joining(", "));
                stringBuilder.append(addString);
                stringBuilder.append(". ");
            }
        }   
        return stringBuilder.toString();
    }

    public Map<String, List<String>> getExceptionAddressMap() {
        return this.exceptionAddressMap;
    }
}
