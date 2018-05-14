import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HelloWorld {
   /** 
     * 字符串编码格式 
     */  
    public static final String ENCODE = "UTF-8";  
      
    /** 
     * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符） 
     *  
     * @param c 
     * @return boolean 
     */  
    public static boolean isLetter(char c) {  
        int k = 0x80;  
        return (c / k) == 0 ? true : false;  
    }  
  
    /** 
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为1,英文字符长度为0.5 
     *  
     * @param  
     *      s 需要得到长度的字符串 
     * @return  
     *      得到的字符串长度 
     */  
    public static double length(String s) {  
        if (s == null) {  
            return 0;  
        }  
        char[] c = s.toCharArray();  
        double len = 0;  
        for (int i = 0; i < c.length; i++) {  
            if (isLetter(c[i])) {  
                len += 0.5;  
            } else {  
                len += 1;  
            }  
        }  
        return Math.ceil(len);  
    }  
  
    /** 
     * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位 
     *  
     * @param origin 
     *          原始字符串 
     * @param len 
     *          截取长度(一个汉字长度按1算的) 
     * @return String, 返回的字符串 
     */  
    public static String getSubString(String origin, int len) {  
        try {  
            // 字符串为空  
            if (length(origin)==0 || (len < 1)) {  
                return "";  
            } 
			/*
            // 截取长度大于字符串长度  
            if (len > length(origin)) {  
            	int rightappend = (int) (len - length(origin));
            	String str = origin;
            	for(int i = 0; i < rightappend; i++) {
    				StringBuffer sb = new StringBuffer();  
    				sb.append(origin).append("00");//右补空格
    				str = sb.toString();
    			}
                return str;  
            }  
            */
			
            StringBuffer buffer = new StringBuffer();  
            char[] array = origin.toCharArray();  
            double currentLength = 0;  
			int index=0;
            for(char c : array){  
                // 字符长度  
                int charlen = String.valueOf(c).getBytes(ENCODE).length;  
                // 汉字按一个长度，字母数字按半个长度  
                if(charlen == 3){  
                    currentLength += 1;  
                }else {  
                    currentLength += 0.5;  
                }  
                if(currentLength <= len){  
                    buffer.append(c);  
					index++;
                } else {  
                    break;  
                }  
            }
			int how = (int)((len-currentLength)/0.5);
			System.out.println(len);
			System.out.println(currentLength);
			System.out.println(how);
			int nextlen = 0;
			if(index < origin.length() ) {
							System.out.println(array[index]);

				nextlen = String.valueOf(array[index]).getBytes(ENCODE).length;
			}
			if(how==-1 && nextlen == 3) {
				how = 1;
			}
			if(how > 0) {
				for(int i =0; i< how; i++) {
					buffer.append("0");
				}	
			}             
            return buffer.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        return null;  
    }  
      
    /** 
     * 测试 
     * @param args 
     */  
    public static void main(String[] args) {  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
        String str = "2018-05-11";
		Date today = new Date();
		try {
			today = sdf.parse(str);  
		}catch(Exception e)
		{
		}
       
		 Calendar c = Calendar.getInstance();  
		 c.setTime(today);  
		 int dayForWeek = 0;  
		 if(c.get(Calendar.DAY_OF_WEEK) == 1){  
		  dayForWeek = 7;  
		 }else{  
		  dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;  
		 }   
		System.out.println(dayForWeek);
		
		System.out.println(getSubString("中卓大健康中卓大健康",6));
    }  
}
