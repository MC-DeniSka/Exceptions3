import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
public class homework3{
    static Map<String, List<String>> map = new HashMap<>();
    static String [] array = new String[0];
    public static void main(String[] args)throws Exception {
        requestData();
        addData();
        System.out.println(map);
    }
    public static void requestData() throws Exception {
        System.out.println("Введите Фамилию, Имя, Отчество, Дату рождения (dd.mm.yyyy), Номер телефона(число без разделителей), Пол (символ латиницей f или m), разделенные пробелом ");
        String text = new String("UTF-8");
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))){
            text = bf.readLine();
        }catch(IOException e){
            throw new IOException("При работе с консолью произошла ошибка");

        }
        array = text.split(" ");
        if(array.length != 6){
            throw new Exception("Введенное количество не совпадает с требуемым количеством данных ");
        }
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date birthday;
        try{
            birthday = format.parse(array[3]);
        }catch(ParseException e){
            throw new ParseException("Неверный формат даты ",e.getErrorOffset());

        }
        long phone;
        try{
            phone = Long.parseLong(array[4]);
        }catch(NumberFormatException e){
            throw new NumberFormatException("Неверный формат телефона ");

        }
        String gender = array[5];
        if(!gender.equalsIgnoreCase("f")&&!gender.equalsIgnoreCase("m")){
            throw new RuntimeException("Неверно введен пол ");
        }
        String fileName = array[0] + ".txt";
        try(FileWriter fileWriter = new FileWriter(fileName)){
        for (String s : array) {
            fileWriter.write("<");
            fileWriter.write(s);
            fileWriter.write(">");
        }
        } catch (IOException e){
        System.out.println(e.getMessage());
        }
        }
        public static void addData(){
        String data = String.join(", ", array);
        ArrayList<String> list = new ArrayList<>();
        list.add(data);

        if (!map.containsKey(array[0])){
        map.put(array[0], list);
        } else {
        map.get(array[0]).add(data);
        }
    }

}