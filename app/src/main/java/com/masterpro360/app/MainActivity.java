package com.masterpro360.app;

import android.app.*;
import android.os.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.view.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {
    LinearLayout root;
    int gold = Color.rgb(225,184,77);
    String screen = "home";

    public void onCreate(Bundle b){
        super.onCreate(b);
        showActivation();
    }

    TextView tv(String text, int sp, int color, boolean bold){
        TextView v = new TextView(this);
        v.setText(text);
        v.setTextSize(sp);
        v.setTextColor(color);
        v.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL);
        v.setPadding(12,8,12,8);
        return v;
    }

    GradientDrawable bg(int color, float r, int strokeColor){
        GradientDrawable g = new GradientDrawable();
        g.setColor(color); g.setCornerRadius(r);
        if(strokeColor != 0) g.setStroke(2, strokeColor);
        return g;
    }

    void base(){
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(60,40,60,40);
        GradientDrawable grad = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{Color.rgb(5,6,12), Color.rgb(8,20,45), Color.rgb(3,5,10)});
        root.setBackground(grad);
        setContentView(root);
    }

    void header(){
        LinearLayout h = new LinearLayout(this);
        h.setOrientation(LinearLayout.HORIZONTAL);
        TextView title = tv("MASTER PRO 360\nPremium TV Streaming Experience", 30, Color.WHITE, true);
        TextView clock = tv("        18:45     🔍   ⚙", 24, gold, true);
        h.addView(title, new LinearLayout.LayoutParams(0,90,1));
        h.addView(clock, new LinearLayout.LayoutParams(420,90));
        root.addView(h);
    }

    public void showActivation(){
        base(); header();
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(55,35,55,35);
        box.setGravity(Gravity.CENTER);
        box.setBackground(bg(Color.argb(220,8,16,34),35,Color.rgb(46,124,255)));
        TextView t = tv("Activate Your Device", 36, Color.WHITE, true);
        TextView sub = tv("Enter Active Code only — no username or password", 20, Color.rgb(183,198,232), false);
        EditText code = new EditText(this);
        code.setTextColor(gold); code.setTextSize(30); code.setHint("Activation Code"); code.setSingleLine(true);
        code.setGravity(Gravity.CENTER); code.setBackground(bg(Color.rgb(16,28,56),18,Color.rgb(46,124,255)));
        Button btn = new Button(this); btn.setText("ACTIVATE"); btn.setTextSize(24); btn.setTextColor(Color.rgb(7,16,32)); btn.setBackground(bg(gold,22,0));
        btn.setOnClickListener(v -> showHome());
        box.addView(t); box.addView(sub); box.addView(code, new LinearLayout.LayoutParams(520,75)); box.addView(btn, new LinearLayout.LayoutParams(330,75));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(780,520); lp.gravity=Gravity.CENTER; lp.topMargin=150;
        root.addView(box,lp);
    }

    public void showHome(){
        screen="home"; base(); header();
        LinearLayout main = new LinearLayout(this); main.setOrientation(LinearLayout.HORIZONTAL);
        root.addView(main, new LinearLayout.LayoutParams(-1,0,1));
        main.addView(sidebar(), new LinearLayout.LayoutParams(320,-1));
        LinearLayout content = new LinearLayout(this); content.setOrientation(LinearLayout.VERTICAL); content.setPadding(30,10,0,0);
        main.addView(content, new LinearLayout.LayoutParams(0,-1,1));
        TextView hero = tv("Featured Entertainment\nMovies, Live TV & Series\n\nApple-style premium interface with dynamic sidebar.", 34, Color.WHITE, true);
        hero.setBackground(bg(Color.argb(180,12,22,44),35,Color.rgb(46,124,255)));
        content.addView(hero, new LinearLayout.LayoutParams(-1,280));
        content.addView(tv("Continue Watching", 26, Color.WHITE, true));
        LinearLayout row = new LinearLayout(this); row.setOrientation(LinearLayout.HORIZONTAL); content.addView(row, new LinearLayout.LayoutParams(-1,240));
        for(int i=1;i<=5;i++) row.addView(card("Movie "+i+"\n★ ★ ★ ★ ☆", i==1), new LinearLayout.LayoutParams(230,210));
    }

    LinearLayout sidebar(){
        LinearLayout s=new LinearLayout(this); s.setOrientation(LinearLayout.VERTICAL); s.setPadding(18,30,18,18);
        s.setBackground(bg(Color.argb(170,7,17,38),30,Color.argb(80,255,255,255)));
        String[] items={"⌂  Home","▣  Live TV","▶  Movies","▤  Series","★  Favorites","☎  Support","⚙  Settings"};
        for(String it:items){
            TextView v=tv(it,22,Color.WHITE,true);
            v.setFocusable(true);
            v.setOnFocusChangeListener((view,has)-> view.setBackground(bg(has?gold:Color.TRANSPARENT,16,0)));
            if(it.contains("Live")) v.setOnClickListener(x->showLive());
            if(it.contains("Movies")) v.setOnClickListener(x->showMovies());
            s.addView(v,new LinearLayout.LayoutParams(-1,68));
        }
        return s;
    }

    TextView card(String text, boolean selected){
        TextView c=tv(text,22,Color.WHITE,true);
        c.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
        c.setBackground(bg(Color.rgb(17,29,57),24,selected?gold:Color.rgb(36,61,112)));
        c.setFocusable(true);
        return c;
    }

    public void showLive(){
        base(); header();
        LinearLayout cols=new LinearLayout(this); cols.setOrientation(LinearLayout.HORIZONTAL); root.addView(cols,new LinearLayout.LayoutParams(-1,0,1));
        cols.addView(list("Categories", new String[]{"◎ All","⚽ Sports","🎬 Movies","📰 News","👶 Kids","ع Arabic","★ Favorites"}), new LinearLayout.LayoutParams(360,-1));
        cols.addView(list("Channels", new String[]{"Premium Sport 1","Premium Sport 2","Premium Sport 3","Premium News 1","Premium Movie 1","Kids Channel"}), new LinearLayout.LayoutParams(520,-1));
        LinearLayout r=new LinearLayout(this); r.setOrientation(LinearLayout.VERTICAL); r.setPadding(25,0,0,0); cols.addView(r,new LinearLayout.LayoutParams(0,-1,1));
        TextView prev=tv("Live Preview\n\nPress OK to Play",34,Color.WHITE,true); prev.setGravity(Gravity.CENTER); prev.setBackground(bg(Color.rgb(2,8,20),30,Color.rgb(46,124,255)));
        r.addView(prev,new LinearLayout.LayoutParams(-1,370));
        r.addView(tv("Program Guide",26,gold,true));
        r.addView(tv("18:00  Match Preview\n19:00  Live Match\n21:00  Highlights Show",24,Color.rgb(232,238,255),false));
    }

    LinearLayout list(String title, String[] arr){
        LinearLayout l=new LinearLayout(this); l.setOrientation(LinearLayout.VERTICAL); l.setPadding(20,20,20,20); l.setBackground(bg(Color.argb(190,7,17,38),30,Color.rgb(29,71,126)));
        l.addView(tv(title,26,gold,true));
        for(String a:arr){ TextView item=tv(a,22,Color.WHITE,false); item.setFocusable(true); item.setOnFocusChangeListener((v,h)->v.setBackground(bg(h?Color.argb(80,225,184,77):Color.TRANSPARENT,15,0))); l.addView(item,new LinearLayout.LayoutParams(-1,68)); }
        return l;
    }

    public void showMovies(){
        base(); header();
        root.addView(tv("Movies  •  Continue Watching  •  Favorites",32,Color.WHITE,true));
        GridLayout grid=new GridLayout(this); grid.setColumnCount(5); root.addView(grid,new LinearLayout.LayoutParams(-1,0,1));
        for(int i=1;i<=10;i++) grid.addView(card("MOVIE "+i+"\nResume • HD", i==1), new ViewGroup.LayoutParams(280,230));
    }
}
