package threadtest.test;

import threadtest.Friendly;

public class FriendTest {
    public static void main(String[] args) {
        final Friendly jareth=new Friendly("jareth");
        final Friendly cory=new Friendly("cory");
        jareth.becomeFriend(cory);
        cory.becomeFriend(jareth);

        new Thread(new Runnable() {
            @Override
            public void run() {
                jareth.hug();
            }
        },"Thread-1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                cory.hug();
            }
        },"Thread-2").start();

    }
}
