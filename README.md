## This is an easy-to-use Spinner for Android
</br>
<img src="https://github.com/yeleaveszi/JJYSpinner/blob/master/spinner.gif">
</br>
This is the basic usage:
```java
        JJYSpinner jjySpinner=(JJYSpinner)findViewById(R.id.spinner);
        final String[] content=new String[]{"ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NIGHT","TEN"};
        jjySpinner.additem(content);
        jjySpinner.setOnSpinnerItemSelectedListener(new JJYSpinner.OnSpinnerItemSelectedListener() {
            @Override
            public void onSelected(int postion) {
                Toast.makeText(MainActivity.this,"["+postion+","+content[postion]+"]",Toast.LENGTH_SHORT).show();
            }
        });
```
You can easily change the style using those methods:
```java
        jjySpinner.setPopupBackgroundColor(Color.BLACK);
        jjySpinner.setPopupResource(R.drawable.popbg);
        jjySpinner.setPupupSelectedResource(R.drawable.popbgselected);
        jjySpinner.setAdapter(new MyAdapter());
        jjySpinner.setLayoutAnimation(new LayoutAnimationController(alpha));
```
