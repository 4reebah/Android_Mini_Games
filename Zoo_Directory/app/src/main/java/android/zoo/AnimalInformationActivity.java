package android.zoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimalInformationActivity extends AppCompatActivity {

    private TextView title;
    private ImageView img;
    private TextView description;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_information);

        extras = getIntent().getExtras();

        title = findViewById(R.id.title);
        img = findViewById(R.id.imageViewAnimalInfo);
        description = findViewById(R.id.description);

        if (extras != null)
        {
            if(extras.getString("title").equals("Fox"))
            {
                title.setText("All About " + extras.getString("title") + "es!");

            }
            else
            {
                title.setText("All About " + extras.getString("title") + "s!");
            }
            img.setImageResource(extras.getInt("image"));
            description.setText(extras.getString("description"));
        }
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
            Intent intent = new Intent(this, ZooInformationActivity.class);
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