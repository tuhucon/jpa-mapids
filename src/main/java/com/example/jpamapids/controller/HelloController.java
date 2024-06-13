package com.example.jpamapids.controller;

import com.example.jpamapids.SimRepository;
import com.example.jpamapids.TestRepository;
import com.example.jpamapids.entity.Author;
import com.example.jpamapids.AuthorRepository;
import com.example.jpamapids.entity.Book;
import com.example.jpamapids.BookRepository;
import com.example.jpamapids.entity.Child;
import com.example.jpamapids.ChildRepository;
import com.example.jpamapids.entity.Parent;
import com.example.jpamapids.ParentRepository;
import com.example.jpamapids.entity.Sim;
import com.example.jpamapids.entity.Test;
import com.zaxxer.hikari.pool.HikariPool;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class HelloController {

    final ParentRepository parentRepository;

    final ChildRepository childRepository;

    final AuthorRepository authorRepository;

    final BookRepository bookRepository;

    final TestRepository testRepository;

    final SimRepository simRepository;

    String simList = "582531666,589727666,587325666,588307666,587781666,586775666,588483666,582253666,589844666,587719666,582553666,587973666,587715666,588527666,587050666,587907666,589907666,585531666,588627666,587752666,568484666,528287666,523353666,528614666 ,522137666,569127666,567980666,569167666,569353666,569775666,568134666,569070666,562913666,569771666,565385666,567610666,568761666,522531666,569644666,528820666,522813666,564139666,522134666,569145666,569171666,528813666,568553666,568543666,564182666,569373666,528378666,568817666,569247666,569177666,528575666,564138666,569124666,528277666,528267666,569782666,528783666,567930666,567713666,568220666,567553666,565130666,566944666,567053666,568367666,568499666,568834666,564181666,528758666,564438666,528795666,569197666,569607666,528711666,568723666,564533666,528607666,528769666,567253666,528737666,567110666,566041888,585540888,522814888,566420888,586094888,565540888,582402888";

    @GetMapping("/tuhucon123")
    public void addTest() throws IOException {
//        for (int i = 0; i <= 1_000_000; i++) {
//            Test test = new Test();
//            test.setName("tu hu con: " + i);
//            test.setStatus(i % 2);
//            test.setAge(i / 10);
//            testRepository.save(test);
//        }
//        System.out.println(testRepository.findNameByIdJapl(1L));

        String[] phoneList = simList.split(",");
        for (int i = 0; i < phoneList.length; i++) {
            String phone = "0" + phoneList[i];
            Document doc = Jsoup.connect("https://simkinhdich.com/sim-phong-thuy?so=" + phone + "&ngay=9&thang=3&nam=1985&gio=18&phut=0&gioitinh=nam&lich=dl").get();
            Sim sim = new Sim();
            sim.setTel(phone);
            sim.setTotal(Float.parseFloat(doc.select(".ketluan").get(0).child(1).select("span").text()));

            String col1 = doc.select(".diemsim").get(0).child(1).select(".itemd").get(0).child(0).child(0).text();
            sim.setCol1(Integer.parseInt(col1.replace("%", "")));
            String col2 = doc.select(".diemsim").get(0).child(1).select(".itemd").get(1).child(0).child(0).text();
            sim.setCol2(Integer.parseInt(col2.replace("%", "")));
            String col3 = doc.select(".diemsim").get(0).child(1).select(".itemd").get(2).child(0).child(0).text();
            sim.setCol3(Integer.parseInt(col3.replace("%", "")));
            String col4 = doc.select(".diemsim").get(0).child(1).select(".itemd").get(3).child(0).child(0).text();
            sim.setCol4(Integer.parseInt(col4.replace("%", "")));
            String col5 = doc.select(".diemsim").get(0).child(1).select(".itemd").get(4).child(0).child(0).text();
            sim.setCol5(Integer.parseInt(col5.replace("%", "")));
            String col6 = doc.select(".diemsim").get(0).child(1).select(".itemd").get(5).child(0).child(0).text();
            sim.setCol6(Integer.parseInt(col6.replace("%", "")));

            simRepository.save(sim);
            System.out.println("done for: " + phone);
        }


        System.out.println("tu hu con");
    }

    @GetMapping("/create")
    public String createChild() throws InterruptedException {
        Parent p = new Parent();
        p.setTitle("title 1");
        Child c = Child.builder()
                .content("tu hu con")
                .parent(p)
                .build();
        System.out.println("********************************");
        childRepository.save(c);

        return "OK";
    }

    @GetMapping("/selectParentChild")
    public String selectParentChild(@RequestParam Long id) throws InterruptedException {
        Parent p = parentRepository.findById(id).get();
        System.out.println("*************************************");
        childRepository.findById(p.getId());
        return "OK";
    }

    @GetMapping("/selectChildParent")
    public String selectChildParent(@RequestParam Long id) throws InterruptedException {
        Child c = childRepository.findById(id).get();
        System.out.println("*************************************");
        parentRepository.findById(id);
        return "OK";
    }

    @GetMapping("/deleteChild")
    public String deleteChild(@RequestParam Long id) {
        Child c = childRepository.findById(id).get();
        childRepository.delete(c);
        return "OK";
    }

    @GetMapping("/deleteParent")
    public String deleteParent(@RequestParam Long id) {
        Parent p = parentRepository.findById(id).get();
        parentRepository.delete(p);
        return "OK";
    }

    @GetMapping("/selectAuthor")
    public String getAuthor(@RequestParam Long id) {
        Author a = authorRepository.findById(id).get();
        for (var book : a.getBooks()) {
            System.out.println(book);
        }
        return "OK";
    }

    @GetMapping("/insertAuthorAndBook")
    public void insertAuthorAndBook() {
        Author a = new Author();
        a.setName("tu hu con");
        a.setAge(ThreadLocalRandom.current().nextInt(1, 100));

        Book b1 = new Book();
        b1.setIsbn("isbn");
        b1.setTitle("title");
        a.getBooks().add(b1);

        Book b2 = new Book();
        b2.setIsbn("isbn");
        b2.setTitle("title");
        a.getBooks().add(b2);

        bookRepository.save(b1);
        bookRepository.save(b2);
        authorRepository.save(a);
    }
}
