## This is an easy-to-use Spinner for Android
</br>
<img src="https://github.com/yeleaveszi/JJYSpinner/blob/master/spinner.gif">
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
