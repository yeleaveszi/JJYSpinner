package android.component.jjyspinner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by JJY on 2016/7/12.
 */
public class JJYSpinner extends EditText{

    private OnSpinnerItemSelectedListener onSpinnerItemSelectedListener;
    private int selectedposition=-1;    //选中索引默认为-1
    private int PopupBackgroundColor=Color.WHITE;   //下拉菜单背景颜色默认为白色
    private int PopupResource=-1;
    private int PupupSelectedResource=-1;
    private Context context;
    private PopupWindow popupWindow;
    private List<String> itemlist=new ArrayList<String>();
    private LayoutAnimationController lac;
    private ListAdapter adapter;
    private int mtextcolor;     //下拉菜单默认文字颜色
    private int mtextsize;      //下拉菜单默认文字大小
    private int mtextgravity;   //下拉菜单默认文字对齐方式
    private LayoutAnimationController defaultlac;   //下来菜单默认对话为Alpha动画
    public JJYSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        this.setGravity(Gravity.RIGHT);
        this.setInputType(InputType.TYPE_NULL);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });
    }
    public void additem(String item){
        itemlist.add(item);
    }
    public void additem(List<String> list){
        itemlist.addAll(list);
    }
    public void additem(String[] arr){
        for(int i=0;i<arr.length;i++)
            itemlist.add(arr[i]);
    }

    public void setOnSpinnerItemSelectedListener(OnSpinnerItemSelectedListener onSpinnerItemSelectedListener) {
        this.onSpinnerItemSelectedListener = onSpinnerItemSelectedListener;
    }

    public void removeAllItem(){
        itemlist.clear();
    }

    public int getItemsSize(){
        return itemlist.size();
    }


    public int getSelectedPosition() {
        return selectedposition;
    }

    public int getPopupBackgroundColor() {
        return PopupBackgroundColor;
    }

    public void setAdapter(ListAdapter adapter){
        this.adapter=adapter;
    }

    public void setPopupBackgroundColor(int popupBackgroundColor) {
        PopupBackgroundColor = popupBackgroundColor;
    }

    public void setLayoutAnimation(LayoutAnimationController lac){
        this.lac=lac;
    }

    public LayoutAnimationController getLayoutAnimation(){
        return lac;
    }

    public int getPopupResource() {
        return PopupResource;
    }

    public void setPopupResource(int popupResource) {
        PopupResource = popupResource;
    }

    public int getPupupSelectedResource() {
        return PupupSelectedResource;
    }

    public void setPupupSelectedResource(int pupupSelectedResource) {
        PupupSelectedResource = pupupSelectedResource;
    }

    public int getMtextgravity() {
        return mtextgravity;
    }

    public void setMtextgravity(int mtextgravity) {
        this.mtextgravity = mtextgravity;
    }

    public int getMtextcolor() {
        return mtextcolor;
    }

    public void setMtextcolor(int mtextcolor) {
        this.mtextcolor = mtextcolor;
    }

    public int getMtextsize() {
        return mtextsize;
    }

    public void setMtextsize(int mtextsize) {
        this.mtextsize = mtextsize;
    }

    private void showPopupWindow(final View thisview) {
        ListView listView=new ListView(context);
        if(PopupResource==-1)
            listView.setBackgroundResource(R.drawable.popbg);
        else
            listView.setBackgroundResource(getPopupResource());

        if(PupupSelectedResource==-1)
            listView.setSelector(R.drawable.popbgselected);
        else
            listView.setSelector(getPupupSelectedResource());

        if(lac==null) {
            AlphaAnimation alpha=new AlphaAnimation(0, 1);
            alpha.setDuration(200);
            defaultlac=new LayoutAnimationController(alpha);
            defaultlac.setOrder(LayoutAnimationController.ORDER_NORMAL);
            listView.setLayoutAnimation(defaultlac);
        }
        else
            listView.setLayoutAnimation(lac);

        if(adapter==null) {
            if(getMtextsize()==0)
                setMtextsize(20);
            if(getMtextcolor()==0)
                setMtextcolor(Color.BLACK);
            if(getMtextgravity()==0)
                setMtextgravity(Gravity.RIGHT);
            listView.setAdapter(new JJYSpinnerAdapter(context, itemlist,getMtextsize(),getMtextcolor(),getMtextgravity()));
        }else{
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedposition=position;
                TextView tv=(TextView) thisview;
                tv.setText(itemlist.get(position));
                onSpinnerItemSelectedListener.onSelected(position);
                popupWindow.dismiss();
            }
        });

            popupWindow=new PopupWindow(listView,
                this.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT, true);
            popupWindow.setTouchable(true);

            popupWindow.setTouchInterceptor(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });

            popupWindow.setBackgroundDrawable(new ColorDrawable(getPopupBackgroundColor()));
            popupWindow.showAsDropDown(thisview);
        }
    public interface OnSpinnerItemSelectedListener{
        void onSelected(int postion);
    }
}


