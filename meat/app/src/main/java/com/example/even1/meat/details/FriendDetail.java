package com.example.even1.meat.details;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.even1.meat.R;

public class FriendDetail extends AppCompatActivity {

    TextView textview;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("文章标题");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textview = (TextView)findViewById(R.id.content);
        textview.setText("话说唐僧师徒四人取到真经后，便上天做了神仙。修成正果的悟空，耐不住天庭的寂寞，回了花果山。而八戒则因取经有功，官复原职做回天蓬元帅，可谓风光无限呀。\n" +
                "可是天庭自宝莲灯事件后，就有各种事情不断发生，妖魔鬼怪层出不穷。凡间又多事，天庭不得安宁，让玉帝费尽心机，甚是头疼。\n" +
                "八戒虽在天庭做官，但是没好好上班，不是睡觉就是玩手机，抽烟、喝酒。改不了他那臭习惯了，有时喝的分不清头脚。玉帝看到这种工作态度，曾无数次发过火。差点就把八戒撤职了。看在唐僧为八戒求情的份上，还是免了。\n" +
                "今玉帝上百度，看头条新闻，得知凡间有事，缅甸炮弹落入云南边境。虽无伤亡，情节严重，而负责南天门（南伞）的二郎神，正值公务出差。此事得赶紧解决，否则会影响天庭的声誉。\n" +
                "玉帝想了想还是去找八戒，他有空，他平时不好好上班，关键时刻可以派上用场。哪知八戒今天恰巧是旷工了，玉帝恼羞成怒。原来是八戒觉得无聊，突然想起多年前与他一起取经的大师兄悟空，不知道这泼猴现在过得怎么样，\n" +
                "决定前去拜访拜访。他纵身一跃，来到花果山上，“哎呦，妈呀，这是花果山吗！？怎么变成这样子，想当年美若天仙境的花果山，竟如此凄惨，真是惨不忍睹。她就急忙问其中的猴子。\n" +
                "你们大王呢？得知大师兄自那次离别，其实也没回花果山。\n" +
                "幸亏还记得猴哥的电话，于是赶紧拨通了电话。原来悟空这些年来去旅游去了，一直在国外。正在回来的路上，今晚就回到阔别多年的花果山。看到八戒给他打电话，很是兴奋，心想，这呆猪还知道给我打电话，回来看我怎么欺负你，于是叫八戒暂且等候花果山。");
    }
}
