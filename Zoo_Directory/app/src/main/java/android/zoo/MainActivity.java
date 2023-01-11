package android.zoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<AnimalItem> animalItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // adding animals  to array
        AnimalItem[] animalList = new AnimalItem[5];
        animalList[0] = new AnimalItem("Horse", R.drawable.horse, "Horses are large, four-legged animal with hooves, a long nose and " +
                "tail, and a mane of hair along its upper back. It is very difficult to learn to ride a horse, but it " +
                "is learned through giving commands to the horse and using reins to guide it. Some interesting facts " +
                "about horses are: \n   1. Horses cannot breathe through their mouth. \n   2. Horses cannot sleep standing up." +
                "\n   3. Horses have 10 different muscles in their ears. ");
        animalList[1] = new AnimalItem("Fox", R.drawable.fox, "Foxes are small to medium-sized bushy-tailed dogs with long fur, pointed ears, and a narrow snout. " +
                "Foxes also have whiskers on their legs and face, which help them navigate. Some interesting facts about foxes are: \n  1. Foxes " +
                "live in underground dens. \n  2. Foxes make 40 different sounds. \n  3. The Red Fox is the most common type of fox. ");
        animalList[2] = new AnimalItem("Cheetah", R.drawable.cheetah,"Cheetahs have slim long, muscular legs, and a " +
                "slender body. It also has a flexible spine, deep chest and special pads on its feet for traction. Some interesting facts " +
                "about cheetahs are: \n  1. Cheetahs are the world's fastest land mammal. \n  2. Cheetah comes from a Hindi " +
                "word, chita. \n  3. Cheetahs hunt during the day. ");
        animalList[3] = new AnimalItem("Panda", R.drawable.panda,"Pandas have a black and white coat, " +
                "with black fur around their eyes and on their ears, muzzle, legs and shoulders. They also have a warm coat" +
                "that allows them to stay warm in the cool mountains. Some interesting facts about pandas: \n  1. They have great camouflage " +
                "for their environment. \n  2. They spend a lot of the day eating. \n  3. Their eyes are different from normal bears.");
        animalList[4] = new AnimalItem("Lion", R.drawable.lion,"Lions are yellow-coated animals " +
                "with strong bodies and powerful teeth and jaws. Lions also have manes, which are determined by age and genetics. " +
                "Some interesting facts abouts lions are: \n  1. A lion's roar can be heard up to 8 km away. \n  2. Lions live in" +
                "groups called prides. \n  3. Female Lions are the main hunter.");

        animalItems = new ArrayList<>();

        for (int i = 0; i < animalList.length; i++) {

            animalItems.add(animalList[i]);
        }
        mAdapter = new CustomAdapter(this,animalItems);
        recyclerView.setAdapter(mAdapter);
    }


    // putting the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.info) {

            Intent intent = new Intent(MainActivity.this, ZooInformationActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.delete) {
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("android.zoo"));
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

}
