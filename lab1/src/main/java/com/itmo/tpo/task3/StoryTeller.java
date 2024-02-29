package com.itmo.tpo.task3;

import com.itmo.tpo.task3.model.impl.*;

import java.util.Set;

public class StoryTeller {

    public static void main(String[] args) {
        Environment location1 = Environment.builder()
                .name("первоначальное окружение")
                .build();
        Environment location2 = Environment.builder()
                .name("подножие монолита")
                .build();
        Environment location3 = Environment.builder()
                .name("подвал").build();

        Passage passage1 = Passage.builder()
                .thisSide(location1)
                .otherSide(location2)
                .name("тропа")
                .locked(false)
                .build();
        Passage passage2 = Passage.builder()
                .thisSide(location2)
                .otherSide(location3)
                .name("огромная резная дверь")
                .locked(true)
                .secretSwitch("панель размером с акр").build();

        Person rodriges = Person.builder()
                .surname("Родригес")
                .location(location1).build();
        Person johansen = Person.builder()
                .surname("Йохансен")
                .location(location1).build();
        Person briden = Person.builder()
                .surname("Брайден")
                .location(location1).build();
        Person willcocks = Person.builder()
                .surname("Уилкокс")
                .location(location1).build();
        Person hawkins = Person.builder()
                .surname("Хоукинс")
                .location(location1).build();
        Person donovan = Person.builder()
                .surname("Донован")
                .location(location1).build();


        location2.getThings().addAll(Set.of(
                new EnvironmentThing("витиевато украшенная перемычка"),
                new EnvironmentThing("порог"),
                new EnvironmentThing("косяк"),
                new EnvironmentThing("изображение головоногого дракона"),
                new EnvironmentThing("покрытый плесенью камень")));

        Monster monster = Monster.builder()
                .location(location3)
                .name("Оно").build();

        Group group = new Group();
        group.addMember(rodriges);
        group.addMember(johansen);
        group.addMember(briden);
        group.addMember(willcocks);
        group.addMember(hawkins);
        group.addMember(donovan);


        System.out.println(rodriges.usePassage(passage1));
        System.out.println(rodriges.generateSound("Я обнаружил нечто интересное"));
        System.out.println(johansen.usePassage(passage1));
        System.out.println(briden.usePassage(passage1));
        System.out.println(willcocks.usePassage(passage1));
        System.out.println(hawkins.usePassage(passage1));
        System.out.println(donovan.usePassage(passage1));

        System.out.println(rodriges.lookAround());

        System.out.println(group.massNotice(passage2));


        System.out.println(johansen.think(passage2, "похожа на дверь амбара"));

        location2.setEvent("относительное расположение всего окружающего меняется");
        System.out.println(willcocks.think(location2, "геометрия пространства нарушена"));

        System.out.println(briden.tryUnlockPassage(passage2,"камень"));

        System.out.println(donovan.tryUnlockPassage(passage2, "левый край"));
        System.out.println(donovan.tryUnlockPassage(passage2,"правый край"));
        System.out.println(donovan.tryUnlockPassage(passage2,"панель размером с акр"));

        location2.setEvent("дверь балансирует в неустойчивом равновесии");
        System.out.println(group.massNotice(location2));
        location2.setEvent("плита двигалась совершенно неестественно в фантасмагорическом пространстве");
        System.out.println(group.massNotice(location2));
        location2.setEvent("мрак вырвался наружу, выплывая в сморщенное горбатое небо, застилая солнце");
        System.out.println(group.massNotice(location2));

        System.out.println(monster.generateGoo());
        System.out.println(monster.usePassage(passage2));
        System.out.println(monster.generateSmell());
        System.out.println(monster.generateSound("отвратительный хлюпающий звук"));

        System.out.println(hawkins.hear());
        System.out.println(hawkins.smell());

    }
}
