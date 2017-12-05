package com.example.even1.meat.Welcome;

        import android.content.Intent;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;

        import com.example.even1.meat.MainActivity;
        import com.example.even1.meat.R;

public class Welcome extends AppCompatActivity {
    Handler handler = new Handler();
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final ImageView imageView = (ImageView)findViewById(R.id.pic);

        /*button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this,MainActivity.class));
            }
        });*/
            handler.postDelayed(new Runnable() {  //使用handler的postDelayed实现延时跳转

            public void run() {
                imageView.setImageResource(R.mipmap.dad);
            }
        }, 1000*1);//2秒后跳转至应用主界面MainActivity
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Welcome.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },1000*2);
    }
}
