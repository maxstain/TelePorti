package org.example.teleporti.Services.Message;

import org.example.teleporti.Entities.Message;

public interface IServiceMessage {

    Message getMessageBySenderIdAndRecieverId(int senderId, int recieverId);
}
