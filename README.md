# FastGuide
fast build a guide pages
快速新手引导页

![image](https://github.com/lcokean/FastGuide/blob/master/art/1.gif)
![image](https://github.com/lcokean/FastGuide/blob/master/art/2.gif)

 [ ![Download](https://api.bintray.com/packages/pengjian1993/maven/fastguide/images/download.svg) ](https://bintray.com/pengjian1993/maven/fastguide/_latestVersion)

HOW TO USE
-----------
```Java
dependencies {
    compile 'com.github.lcokean:fastguide:0.2'
}
```


```Java
final GuidePage guidePage = new GuidePage();
TextView textView1 = new TextView(getApplicationContext());
textView1.setText("1");
textView1.setTextSize(50);
textView1.setGravity(Gravity.CENTER);
textView1.setBackgroundColor(Color.parseColor("#ff00ddff"));
guidePage.addPage(textView1); // 添加页面1
TextView textView2 = new TextView(getApplicationContext());
textView2.setText("2");
textView2.setTextSize(50);
textView2.setGravity(Gravity.CENTER);
textView2.setBackgroundColor(Color.parseColor("#ff00ddff"));
guidePage.addPage(textView2); // // 添加页面2
guidePage.addPage(this, R.layout.activity_main); // 添加页面3
// 页面被pageView加载监听
guidePage.setOnViewLazyInitializeListener(new ViewLazyInitializeListener() {
    @Override
    public void onViewLazyInitialize(View view, int position) {
        Toast.makeText(getApplicationContext(), "view in " + position + " is Initialized", Toast.LENGTH_SHORT).show();
    }
});
//guidePage.setOrientation(GuidePage.VERTICAL);  // 滑动方向 defult is HORIZONTAL
// 页面被pageView选中监听
guidePage.setOnViewSelectedListener(new GuidePage.OnViewSelectedListener() {
    @Override
    public void onViewSelected(View view, int position) {
        if (position != 2) return;
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "onClick", Toast.LENGTH_SHORT).show();
                guidePage.finish();
            }
        });
    }
});
guidePage.show(this); // 显示Guide
```
