package org.bitmanipulation;

import java.util.concurrent.TimeUnit;

public class AweSome {

    public static void main(String[] args)
        {
            int i = 1;
            int sum = 0;
            long startTime = System.nanoTime();
//            sum = 1 +2;
//            sum = 86565 + 96565;
//            sum = 100+200+300+400+500+600+700+800+900+1000;
//            sum = 1+20+30+40+50+60+70+80+90+100+110+120+130+140+150+160+170+180+190+200+210+
//                  111+211+311+411+511+611+7777+888+96+160+5511+1542+1543+174+4515+1766+1547+5518+1329+2320+2321+
//                  132+232+3545+664+6565+666+76564+86565+96565+1560+1134+11212+1323+14344+1554+15466+1117+1338+1449+2110+221;
            while (i <= 1000) {
                sum = sum + i;
                ++i;
            }
            System.out.print(sum);
            long endTime = System.nanoTime();
            System.out.print("Time Taken:" +   (endTime - startTime));
    }
}
