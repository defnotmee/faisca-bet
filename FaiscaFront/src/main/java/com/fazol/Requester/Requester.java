package com.fazol.Requester;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import com.fazol.App;
import com.fazol.Requester.RException.InvalidCpfException;
import com.fazol.Requester.RException.InvalidDataException;
import com.fazol.Requester.RException.InvalidEmailException;
import com.fazol.Requester.RException.PermissionException;
import com.fazol.Requester.RException.TooPoorException;
import com.fazol.Requester.RException.UserNotFoundException;

public abstract class Requester{
    
    protected static String getFormDataAsString(Map<String, String> formData) {
        StringBuilder formBodyBuilder = new StringBuilder();
        for (Map.Entry<String, String> singleEntry : formData.entrySet()) {
            if (formBodyBuilder.length() > 0) {
                formBodyBuilder.append("&");
            }
            formBodyBuilder.append(URLEncoder.encode(singleEntry.getKey(), StandardCharsets.UTF_8));
            formBodyBuilder.append("=");
            formBodyBuilder.append(URLEncoder.encode(singleEntry.getValue(), StandardCharsets.UTF_8));
        }
        return formBodyBuilder.toString();
    }

    protected static String getAddress(String page) {
        return "http://" + App.serverIp + ":8080/" + page;
    }

    public abstract Object makeRequest(List<String> arguments) throws InvalidEmailException, TooPoorException,
    PermissionException, UserNotFoundException, InvalidCpfException, InvalidDataException, IOException;

}
