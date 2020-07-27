## Havuz Problemi

    Dinamik Graf Yapısı Projesi

<i>Şeyma Nur MUTLU ve Melike OĞUZ

Kocaeli Üniversitesi Bilgisayar Mühendisliği</i>


<h3><code>ÖZET</code></h3>

**“Havuz Problemi"** adlı programda kullanıcı tarafından girilen musluk sayısı adedince node oluşturulur. Daha sonra kullanıcı, başlangıç ve bitiş düğümleri seçimi yapar. Tüm bu işlemlerden sonra program, havuz hangi yol ile en kısa sürede dolar ve boşaltılır hesabı yaptıktan sonra sonuçları çıktı olarak verir.

<h4><code>1- Giriş</code></h4>

Havuz Problemi programımız graf mantığı ile çalışmaktadır. Bu yüzden musluklar ve boru hattı graf yapısındaki node ve edgelere karşılık gelmektedir.Şimdi programımızı anlatmaya başlayalım.

Program çalıştırıldıktan sonra öncelikle ekrana <b>kaç adet musluk sayısı</b> olacağı kullanıcıya sorulur. Daha sonra musluk adedince nodelar ekrana bastırılır. Bu işlemler yapılırken her şey dinamik olması gerektiği için <code>dinamic graph</code> yapısı kullanılmıştır. 

Kullanıcının kullanımı için gayet kullanışlı olan bir arayüz ile başlangıç ve bitiş düğümleri sorulur. Düğümler belirlendikten sonra bu düğümler renklendirilir. Daha sonra akış sisteminin devamının yapılabilmesi için arayüz üzerinden <b>hangi musluk ile hangi musluk arasında bağlantı olsun</b> ve bu hattın <b>kapasite miktarı ne kadar olmalı</b> gibi sorular sorulur. 

Sorular cevaplandırıldıkça <code>TableView</code> üzerinden kontrol edilmesi kolay olsun diye her defasında bu kutucuğa veriler bastırılır.
Girilen bilgiler doğrultusunda havuzu dolduracak suyun geçeceği tesisatın görüntüsü ortaya çıkar. 

Daha sonra yine arayüz üzerinden kullanıcı <code>Maksimum Akış</code> veya <code>Mimimum Akış</code> butonlarına tıklayarak hangi boru hattı ve musluklar açılırsa havuz en kısa sürede dolar ve boşaltılır diye görür. Görsel açıdan bu bilgiler kullanıcıya gösterildikten sonra program sona erer.

</br>
<h4><code>2- Temel Bilgiler</code></h4>

* Program, Java dilinde geliştirilmiş olup debug
ve release işlemleri <code>Eclipse ID</code> kullanılarak
yapılmıştır ile yapılmıştır.

* Arayüz için <code>JavaFx</code> kullanılıp gerekli jarlar
projeye entegre edilmiştir. JavaFx'in tasarımı
<code>Scene Builder</code> kullanılarak geliştirilmiştir.


[<img src="https://hayalindekiyasam.files.wordpress.com/2020/07/butt2.png">](https://www.youtube.com/watch?v=ejx3VxuIc8w)

</br>
<h4><code>3- Tasarım</code></h4>

<p>Proje aşağıdaki başlıklar altında geliştirilmiştir.</p>

<h5><code>3.1 Yazılım Tasarımı</code></h5>
</br>

<code><b>Main (class):</b></code> Projenin başlatıldığı classtır. Burada bir adet **Scene** oluşturulur. Bu ekran üzerinden kullanıcıdan musluk sayısı verisi alınır. **Cizdir** adlı butonuna basılarak graf yeni pencerede açılır.
Layered eklenerek daha önce hazırlanmış olan arayüzler arasında geçiş yapabilmek için bir adet **Anchor Pane** oluşturulmuştur. Daha sonra <b>Grid Paneler</b> bu Anchor Pane'in çocukları olarak eklenmiştir. Sırası ile arayüzler arasında geçişler yapılır.

<code><b>MainControlller (class):</b></code> Main classındaki kullanıcı tarafından alınan musluk sayısı bilgisi, bu classta bulunan <code>TakeMuslukSayisi</code> adlı metoda gönderilir ve düğümler oluşturulur. Diğer fxml formatındaki arayüzler arasında geçiş yapılır. 

<code><b>Anchor (fxml):</b></code> İçerisinde sadece Anchor Pane bulunur ve Grid Paneler arasında geçiş yapılabilmek için oluşturulmuştur.

<code><b>Application (css):</b></code> Arayüz üzerindeki tasarımların görüntüleri için css dosyası oluşturulmuştur.

<code><b>Main (fxml):</b></code> Program başlatıldığında ilk olarak kullanıcının gördüğü ekrandır. txtMuslukSayisi adlı <code>textField'a</code> musluk sayısı girilir. Daha sonra **Cizdir** adlı butona tıklanarak diğer arayüze geçiş sağlanır.  

<code><b>First (fxml):</b></code> Kullanıcıdan başlangıç, bitiş düğümünün, hangi musluk ile hangi musluk arasında bağlantı kurulmasının istendiğinin, kapasite miktarının ve hangi akış türünün seçilmek istendiğinin sorulduğu arayüzdür.

<code><b>FirstPageController (class):</b></code> First adlı arayüzün arkaplanında yapılan fonksiyonları barındıran sınıftır.

<code><b>EdgeInfo (class):</b></code> Klavye üzerinden kullanıcıdan alınan musluk bağlantı bilgilerinin yani birinci düğümün, ikinci düğümün  ve kapasitenin tutulduğu sınıftır.


</br>
<h4><code>4- Karşılaşılan Sorunlar ve Çözümleri</code></h4>

 <h6><code>4.1- Javafx.scene.layout.AnchorPane Can Not Be Cast o javafx.scene.layout.GirdPane Hatasının Alınması</code></h6>

* Daha önce Anchor Pane kullanarak oluşturduğumuz arayüzlerimizi Grid Pane olarak bir nesne listesinde tutmak isteidiğimizde karşımıza çıkan hata türüdür. Çözümü için "First.fxml" adlı dosyanın kodlarını inceledik ve <code>< AnchorPane> < /AnchorPane ></code> tagleri <code>< GridPane > < /GridPane ></code> olarak değiştirilmiştir.
 </br></br>
 
 </br>
 <h4><code>5- Kullanılan Algoritmalar</code></h4>


<ins>**5.1.1-  Ford- Fulkerson Algoritmasının
Açıklaması (Max Flow)**</ins>

    
* Ford Fulkerson (Çizge Teoremi) algoritması
tarihi, 1736 yılında ilk kez Leonhard Euler
tarafından çözümlenen, Königsberg’in 7
köprüsü (graf teorisi nedir) problemi ile
başlamaktadır. Birçok problem Euler
tarafından bulunan bu teorem üzerinden
çözümlenmektedir.

* Bu problemlerden birisi de azami akış (max
flow) problemidir.Herhangi bir başlangıç
noktasından hedef noktasına olan azami akışı
belirlemek için kullanılan Ford Fulkerson
Algoritması 1956 yılında L.R Ford ve D.R
Fulkerson tarafından bulunmuştur.
 
* Günlük hayattaki önemi çok büyük olan bu
algoritma kullanıldığı bazı yerler; elektrik
devreleri, bilgisayar ağ sistemlerinin
optimizasyonu, trafik akışını yönlendirme,
elektrik, su ve atık su akışını yönlendirmede
kullanılmaktadır.
 
<ins>**5.1.2- Ford- Fulkerson Algoritmasının
Karmaşıklık  Analizi**</ins>


   * Maksimum akışı bulmak için kullandığımız algoritmanın karmaşıklık analizi, <code>O(VE^{2})t'dır.</code>

<ins>**5.1.3-  Ford-Fulkerson Algoritmasının Pseudo Kodu**</ins>

* Başlangıç olarak seçilen düğümden başla.

* Başlangıç düğümünden bitiş düğümüne bir
artırım yolu varsa, akışa bu yol akışını ekle.

* Akışı sonuç olarak döndür.

<ins>**5.2.1 Karger’s Algoritmasının Açıklaması (Min Cut)**</ins>

* Bilgisayar bilimi ve graf teorisinde Karger'in
algoritması, bağlı bir grafın minimum kesimini
hesaplamak için rastgele bir algoritmadır.
David Karger tarafından icat edildi ve ilk
olarak 1993 yılında yayınlandı.


* Yönlendirilmemiş ve ağırlıksız bir graf verildiğinde, en küçük kesimi (grafiği iki bileşene ayıran en az kenar sayısı) bulur.

<ins>**5.2.2- Karger’s Algoritmasının Karmaşıklık Analizi (Min Cut)**</ins>

* Minimum akışı bulmak için kullandığımız algoritmanın karmaşıklık analizi, <code>E=O(V2)'dir.</code>

<ins>**5.2.3- Karger’s Algoritmasının Pseudo Kodu**</ins>

* Orijinal grafın kopyası olarak contracted graf CG'yi başlat.

* 2'den fazla köşe varken.

  * Kasılmış grafikte rastgele bir kenar (u,v) seçin.

  * u ve v'yi tek bir tepe noktasında birleştirin (veya contract) (contracted grafı güncelle).

  * Self-loopları sil

* İki köşe ile temsil edilen kesimi sonuç

olarak döndür.

</br>
<h4><code>6. Havuz Problemi Pseudo Kod</code></h4>

**1-** Kullanıcıdan musluk sayısını al.

**2-** Cizdir butonuna basıldıktan sonra girilen musluk sayısı adedince node oluştur. <code>First.fxml</code> dosyasını çalıştır.

**3-** Oluşturulan nodeları <code>GraphStream</code> penceresine bas.

**4-** Kullanıcıdan başlangıç düğümünü ve bitiş düğüm bilgilerini al.

**5-** Başlangıç ve bitiş düğümlerini mavi renkle belirtip ekrana bas.

**6-** Kullanıcıdan hangi musluklar arasında bağlantılı kurulacağı bilgisini ve kapasite miktarını al.

**7-** Düğümler arası geçiş bilgilerini ve kapasite miktarı bilgilerini <code>TableView'de</code> göster.

**8-** Düğümler arasındaki geçiş bilgilerini kullanarak son grafı ekrana bas.

**9-** Maksimum Akış butonuna basıldığında <code>maxFlow</code> metodunu çalıştır.

**10-** Minimum Akış butonuna basıldığında <code>minFlow</code> metodunu çalıştır.

**11-** Programı sonlandır.

</br>
<h4><code>7- Kazanımlar</code></h4>


**1-** Dinamik graf yapısının kullanılması

**2-** JavaFx ile arayüz tasarımı, TableView gibi
birçok nesnenin kullanımının öğrenilmesi

**3-** Min Cut ve Max Flow algoritmalarının
çalışma mantığı

**4-** GitHub kullanımı

**5-** Scene Builder kullanımı

**6-** Karmaşıklık analizinin nasıl yapılacağını
öğrenmek

</br>
<h4><code>8- Projenin Videosu</code></h4>

![](https://hayalindekiyasam.files.wordpress.com/2020/07/pool-problem-1.gif)

</br>
<h4><code>9- Kaynakça</code></h4>

[1] [http://graphstream-project.org/](http://graphstream-project.org/)

[2] [https://www.geeksforgeeks.org/minimum-cut-in-a-directed-graph/](https://www.geeksforgeeks.org/minimum-cut-in-a-directed-graph/)

[3] [https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/](https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/)

[4] [https://stackoverflow.com/questions/35956527/javafx-javafx-scene-layout-anchorpane-cannot-be-cast-to-javafx-scene-layout-bo](https://stackoverflow.com/questions/35956527/javafx-javafx-scene-layout-anchorpane-cannot-be-cast-to-javafx-scene-layout-bo)

[5] [https://code.makery.ch/tr/library/javafx-tutorial/part1/](https://code.makery.ch/tr/library/javafx-tutorial/part1/)

[6] [https://www.youtube.com/watch?v=5GsdaZWDcdY](https://www.youtube.com/watch?v=5GsdaZWDcdY)

[7] [https://www.youtube.com/watch?v=zWTzZ4sNAFw](https://www.youtube.com/watch?v=zWTzZ4sNAFw)

[8] [https://en.wikipedia.org/wiki/Ford%E2%80%93Fulkerson_algorithm](https://en.wikipedia.org/wiki/Ford%E2%80%93Fulkerson_algorithm)

[9] [https://www.muhendisbeyinler.net/ford-fulkerson-algoritmasi/](https://www.muhendisbeyinler.net/ford-fulkerson-algoritmasi/)

[10] [https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/](https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/)

[11] [https://www.geeksforgeeks.org/kargers-algorithm-for-minimum-cut-set-1-introduction-and-implementation/](https://www.geeksforgeeks.org/kargers-algorithm-for-minimum-cut-set-1-introduction-and-implementation/)



