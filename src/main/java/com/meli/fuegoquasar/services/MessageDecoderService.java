package com.meli.fuegoquasar.services;

import com.meli.fuegoquasar.exceptions.InvalidMessageException;
import com.meli.fuegoquasar.models.Satellite;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageDecoderService {

    public String getMessage(List<Satellite> satellites){
        List<List<String>> messagesList = satellites.stream().map(Satellite::getMessage).collect(Collectors.toList());
        int messageListSize = messagesList.get(0).size();
        StringBuilder finalMessage = new StringBuilder();
        StringBuilder tempSb = new StringBuilder();
        if (!messagesList.stream().allMatch(message -> message.size() == messageListSize)) {
            throw new InvalidMessageException("ERROR: UNREADABLE MESSAGE. Message size is not equal on every Satellite");
        }
        for (int i = 0; i < messageListSize; i++) {
            for (List<String> itemList : messagesList) {
                if (!itemList.get(i).equals("")) {
                    if (tempSb.length() == 0) {
                        tempSb.append(itemList.get(i));
                        if (i == messageListSize - 1) {
                            finalMessage.append(tempSb.toString());
                        } else {
                            finalMessage.append(tempSb.toString()).append(" ");
                        }
                    }
                }
            }
            tempSb.setLength(0);
        }
        return finalMessage.toString();
    }

}
