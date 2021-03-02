package com.company.main;

import com.company.CommandPattern.Commands.LightDownCommand;
import com.company.CommandPattern.Commands.LightOffCommand;
import com.company.CommandPattern.Commands.LightOnCommand;
import com.company.CommandPattern.Commands.LightUpCommand;
import com.company.CommandPattern.Invoker.LightInvoker;
import com.company.CommandPattern.Receiver.Light;
import com.company.FactoryMethodPattern.Factory.AnimalFactory;
import com.company.FactoryMethodPattern.Factory.BalanceAnimalFactory;
import com.company.FactoryMethodPattern.Factory.RandomAnimalFactory;
import com.company.Singleton.Singleton;
import com.company.abstractFactoryPattern.ThemeFactory.DarkTheme;
import com.company.abstractFactoryPattern.ThemeFactory.LightTheme;
import com.company.adapter.RoundHole;
import com.company.adapter.RoundPeg;
import com.company.adapter.SquarePeg;
import com.company.adapter.SquarePegAdapter;
import com.company.ObserverPattern.observable.WeatherStation;
import com.company.ObserverPattern.observers.PhoneDisplay;
import com.company.strategy_pattern.Father.Duck;
import com.company.strategy_pattern.children.CityDuck;

public class Main {

    public static void main(String[] args) {
        //abstractFactoryPattern();
        //FactoryMethodPattern();
        //strategyPattern();
        //observerPattern();
        //decoratorPattern();
        //singleton();
        //CommandPattern();
        AdapterPattern();
    }

    private static void AdapterPattern() {
        // Round fits round, no surprise.
        RoundHole hole = new RoundHole(5);
        RoundPeg rpeg = new RoundPeg(5);
        if (hole.fits(rpeg)) {
            System.out.println("Round peg r5 fits round hole r5.");
        }

        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(20);
        // hole.fits(smallSqPeg); // Won't compile.

        // Adapter solves the problem.
        SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largeSqPegAdapter = new SquarePegAdapter(largeSqPeg);
        if (hole.fits(smallSqPegAdapter)) {
            System.out.println("Square peg w2 fits round hole r5.");
        }
        if (!hole.fits(largeSqPegAdapter)) {
            System.out.println("Square peg w20 does not fit into round hole r5.");
        }
    }

    private static void CommandPattern() {
        Light light = new Light();
        LightInvoker lightInvoker = new LightInvoker(
                new LightOnCommand(light),
                new LightOffCommand(light),
                new LightUpCommand(light),
                new LightDownCommand(light));
        lightInvoker.OnClick();
        lightInvoker.UpClick();
    }

    private static void singleton() {
        Singleton singleton = Singleton.getInstance();
        singleton.testString = "this is singleton";
        System.out.println(singleton.testString);
        singleton = Singleton.getInstance();
        System.out.println(singleton.testString);
    }

    private static void abstractFactoryPattern() {
        DarkTheme darkTheme = new DarkTheme();
        System.out.println("DARK:");
        System.out.println(darkTheme.TEXT_COLOR().getColor());
        System.out.println(darkTheme.BACKGROUND_COLOR().getColor());
        LightTheme lightTheme = new LightTheme();
        System.out.println("LIGHT:");
        System.out.println(lightTheme.BACKGROUND_COLOR().getColor());
        System.out.println(lightTheme.TEXT_COLOR().getColor());
    }

    private static void FactoryMethodPattern() {
        RandomAnimalFactory randomAnimalFactory = new RandomAnimalFactory();
        BalanceAnimalFactory balanceAnimalFactory = new BalanceAnimalFactory();
        AnimalFactory animalFactory;
        for (int i = 0; i < 10; ++i) {
            animalFactory = randomAnimalFactory;
            System.out.println(animalFactory.CreateAnimal().GetSound());
            animalFactory = balanceAnimalFactory;
            System.out.println(animalFactory.CreateAnimal().GetSound());
        }
    }

    private static void decoratorPattern() {
        //Espresso espresso = new Espresso();
        //Beverage beverage = new Tea(espresso);
        //System.out.println(beverage.cost());
    }

    private static void observerPattern() {
        WeatherStation station = new WeatherStation();
        PhoneDisplay display = new PhoneDisplay(station);
        station.Add(display);
        station.setTemperature(10);
        station.Notify();
        station.setTemperature(20);
        station.Notify();
        station.setTemperature(30);
        station.Notify();
        station.setTemperature(40);
        station.Notify();
    }

    public static void strategyPattern() {
        CityDuck cityDuck = new CityDuck();
        Duck duck = new Duck(cityDuck, cityDuck, cityDuck, cityDuck);
        duck.fly();
    }
}
