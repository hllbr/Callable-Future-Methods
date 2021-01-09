
import static java.lang.System.out;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    
    public static void main(String[] args) {
        
        /*
        Threadlerden değer dönmemizi sağlayan ve daha sonra bu değeri elde etmemizi sağlayan Callable-Future Interfacelerin kullanımları
        
        Şuana kadar yapmış olduğum projelerimde Threadlerimi Runnable interface'i üzerinden oluşturmuştum.Runnable içerisinde bulunan Run metodunu implemente ederek Threadlerime belirli görevler vermiştim
        
        Ancak Runnable interface'te yani Threadler işlerini bitirdikten sonra run metodundan herhangi bir değer döndüremiyordum.
        
        Ancak Callable Interface Kullanarak ve Future Interface'ini kullanarak bu Threadlerim işlerini bitirdikten sonra herhangi bir değer döndürmesini ve bu değeri almayı mümkün kılabilirim.
        
        Bunun için ExecutorService oluşturarark işlemlerime başlamam gerekiyor.
        */
        ExecutorService exeser = Executors.newFixedThreadPool(1);//Aynı anda kaç Thread Kullanılabilir.Aynı anda Kaç Thread Çalışabilir bunu belirliyoruz.
        //tek bir thread kullanacağım için bunu belirttim .Bir Threadimize bir adet Task(GÖrev) ataması yapmam gerekiyor.Bir işi elimdeki threde atamam gerkiyor.
        exeser.submit(new Runnable() {
            /*
            bizim buraya bir adet runnable interface'i implemente eden bir thread göndermem gerekiyordu.Ancak ben burada herhangi bir anonim class yazarakta bu işlemi gerçekleştirebiliyorum.
            
            */
            @Override
            public void run() {
                try {
                    // run metodum void bir metod olduğu için herhangi bir değer döndüremiyorum.Burada Run metodunu yazığımda sadece thredim çalışıyor ve threadim hgerhangi bir değer döndüremiyor.
                    
                    Random random = new Random();
                    
                    System.out.println("Thread Çalışıyor...");
                    //uyutma süresini rastgele yapmak için random class'tan ürettiğim objeden yararlanmak istiyorum
                    int rstsure = random.nextInt(3550)+580;
                    Thread.sleep(rstsure);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Thread Çıkıyor...");
                
                
            }
        });
        exeser.shutdown();
      //  exeser.awaitTermination(0, TimeUnit.DAYS);burada task çok uzun sürmeyeceği için burada gereksizdir.
      /*
      burda run metodumuz void bir metod olduğu için herhangi bir değer döndüremiyoruz.burda herhangi bir değer döndürmek için runnable dışında bir interfacei kullanmamız gerekiyor.
      bizim kullanacağımız interfacete callable Interfacei bu Interfacete RunnableInterface gibi bunun içinde run metodu değil call metodu çalışacak
      call metodunda biz herhangi bir metodu çağırdığımız yere döndürebileceğiz
      */
    }
}
