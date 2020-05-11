import org.glassfish.grizzly.utils.ArrayUtils;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import java.util.*;


public class MG extends TelegramLongPollingBot {
    int count = 0;
    int[] numbers = new int[101];
    String[] players = new String[100];
    int[] scores = new int[100];

    public void onUpdateReceived(Update update) {

        Message msg = update.getMessage(); // Это нам понадобится
        String txt = msg.getText();
        String word = setRan();
        String cursed = setRan();
        String player = msg.getFrom().getFirstName() + " " + msg.getFrom().getLastName();


            if( txt.equals("/help")){
                sendMsg(msg, "Правила прості (як і у всіх хороших ігор): якщо ти під час переписки пишеш слово, яке загадав бот, то твій рахунок збільшується на +2. " +
                        "Однак будь обачним! Деякі слова, які загадає бот, можуть результувати -1! Після відгадування бот змінює ключове слово.");}
            if( txt.toLowerCase().indexOf(word.toLowerCase()) != -1) {
                scores[ ArrayUtils.indexOf(players, player)] = scores[ ArrayUtils.indexOf(players, player)] + 2 ;
                System.out.println(scores[ ArrayUtils.indexOf(players, player)]);
                sendMsg(msg, "Вітаю +1! Ви вгадали слово " + " <" + word + "> " +  " Ваш рахунок: " + scores[ ArrayUtils.indexOf(players, player)]);
            }
            if( txt.toLowerCase().indexOf(cursed.toLowerCase()) != -1) {
                scores[ ArrayUtils.indexOf(players, player)]  = scores[ ArrayUtils.indexOf(players, player)] - 1;
                System.out.println(scores[ ArrayUtils.indexOf(players, player)]);
                sendMsg(msg, " Халепа! Cлово " + " <" + cursed + "> " + " було прокляте! -1." + " Ваш рахунок: " + scores[ ArrayUtils.indexOf(players, player)]);



            }
            if(txt.equals("/join") ) {
                if(Arrays.asList(players).contains(player)){
                    sendMsg(msg, "ви уже в грі лол");
                }else {
                    players[count] = player;
                    sendMsg(msg, player + ", вітаю у грі!");
                    count++;
                }

            }

            if(txt.equals("/results") ) {
//                int i;
//                int large[] = new int[5];
//
//
//                for (int j = 0; j < scores.length; j++) {
//                    for (i = 4; i >= 0; i--) {
//                        if (scores[j] > large[i]) {
//                            if (i == 4) {
//                                large[i] = scores[j];
//                            }
//                            else{
//                                int temp = large[i];
//                                large[i] = scores[j];
//                                large[i+1] = temp;
//                            }
//                        }
//                    }
//                }
//                for (int j = 0; j<5; j++)
//                {
//                    System.out.println("Largest "+ j + ":"+ large[j]);
//                }
                StringBuilder str = new StringBuilder("рези: " + "\n");
                for (int i = 0; i < 101; i++) {
                    if(players[i] == null){
                        break;
                    }
                    str.append(players[i] + " " + scores[ ArrayUtils.indexOf(players, players[i])] + "\n");

                }
                sendMsg(msg, str.toString() );

            }





        }



    @SuppressWarnings("deprecation") // Означает то, что в новых версиях метод уберут или заменят
    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        // Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
        s.setText(text);
        try { //Чтобы не крашнулась программа при вылете Exception
            sendMessage(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    String setRan() {
        String[] words = {"я", "дідько",};//словник
        int randNum = new Random().nextInt(words.length);
        String rs = words[randNum];
        System.out.println(rs);
        return rs;
    }





    public String getBotUsername() {
        return "MinorGame_bot";
    }

    public String getBotToken() {
        return "873535986:AAGlYGbQFu77Kw413iWDJLmwnfAyny9sgS0";
    }
}



