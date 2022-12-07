package truong.poly.firebasebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    List<Book> books;

    public Adapter(List<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    Context context;
    @Override
    public int getCount() {

        return books.size();
    }

    @Override
    public Book getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v= LayoutInflater.from(context).inflate(R.layout.listitem,viewGroup,false);
        TextView tentruyen=v.findViewById(R.id.tentruyen);
        TextView gia=v.findViewById(R.id.gia);
        TextView tacgia=v.findViewById(R.id.tacgia);
        Book book =getItem(i);
        tentruyen.setText("Tên truyện:"+book.name);
        gia.setText("Gía:"+book.price);
        tacgia.setText("Tác giả:"+book.author);
        return v;
    }
}

