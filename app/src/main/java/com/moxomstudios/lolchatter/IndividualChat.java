package com.moxomstudios.lolchatter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;

import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;

import java.util.ArrayList;
import java.util.List;

public class IndividualChat extends AppCompatActivity {

    private ChatManager cm;
    List<String> messages;
    Chat newchat;
    ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        String message = intent.getStringExtra("username");


        messages = new ArrayList<String>();



        myAdapter=new
                ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                messages);
        ListView myList=(ListView)
                findViewById(R.id.listView2);
        myList.setAdapter(myAdapter);


        TextView nameTextView = (TextView) findViewById(R.id.textView);
        nameTextView.setText(message);




        ChatManager chatmanager  = ChatManager.getInstanceFor(XMPPTCPConnectionHolder.getConnection());

        newchat= chatmanager.createChat("sum19332813@pvp.net/xiff", new ChatMessageListener() {
            @Override
            public void processMessage(Chat chat, org.jivesoftware.smack.packet.Message message) {
                Log.i("LOL", "Received message: " + message);
                String messageText = ""+message;
                messageText = messageText.substring(messageText.indexOf("<body>")+"<body>".length(),messageText.indexOf("</body>"));
                messages.add("them: " + messageText);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.notifyDataSetChanged();
                    }
                });

            }
        });


        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                Log.i("LOL","sending Message "+actionId);

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Log.i("LOL", "sending Message");

                    sendMessage(v.getText().toString());
                    messages.add("Me: " + v.getText().toString());
                    myAdapter.notifyDataSetChanged();
                    v.setText("");
                    handled = true;
                }
                return handled;
            }
        });
    }


    private void sendMessage(String textToSend)
    {
        try {
            newchat.sendMessage(textToSend);
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
        }
    }

}
