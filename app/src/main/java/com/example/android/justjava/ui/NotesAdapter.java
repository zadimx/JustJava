package com.example.android.justjava.ui;

import android.database.Cursor;
import android.os.Binder;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.justjava.DbActivity;
import com.example.android.justjava.R;
import com.example.android.justjava.db.NotesContract;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Адаптер для заметок
 */
public class NotesAdapter extends CursorRecyclerAdapter<NotesAdapter.ViewHolder> {

    private final OnNoteClickListener onNoteClickListener;
    private final OnNoteClickListenerPhone onNoteClickListenerPhone;
    private final OnNoteClickListenerMap onNoteClickListenerMap;


    private static int count=0;
    public NotesAdapter(Cursor cursor, OnNoteClickListener onNoteClickListener, OnNoteClickListenerPhone onNoteClickListenerPhone, OnNoteClickListenerMap onNoteClickListenerMap) {
        super(cursor);

        this.onNoteClickListener = onNoteClickListener;
        this.onNoteClickListenerPhone = onNoteClickListenerPhone;
        this.onNoteClickListenerMap = onNoteClickListenerMap;
    }




    public void editAxis(ViewHolder viewHolder, String[] a){

        int axis1ColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_AXIS1);
        String axis1 = cursor.getString(axis1ColumnIndex);
        if (axis1 != null && axis1.matches("-?\\d+")) {
            if (Integer.parseInt(axis1) < 0 ) {
                viewHolder.axis1.setText(0+" т");
            }
            else if (Integer.parseInt(axis1) > 0 && axis1.matches("-?\\d+")) {
//                viewHolder.axis1.setText(Integer.parseInt(axis1)+" кг");
                viewHolder.axis1.setText(a[0]+" кг");
            }
        }


        int axis2ColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_AXIS2);
        String axis2 = cursor.getString(axis2ColumnIndex);
        if (axis2 != null && axis2.matches("-?\\d+")) {
            if (Integer.parseInt(axis2) < 0) {
                viewHolder.axis2.setText(0+" т");
            }
            else if (Integer.parseInt(axis2) > 0 && axis2.matches("-?\\d+")) {
//                viewHolder.axis2.setText(Integer.parseInt(axis2)+" кг");
                viewHolder.axis2.setText(a[1]+" кг");
            }
        }


        if (axis1 != null && axis2 != null && axis1.matches("-?\\d+") && axis2.matches("-?\\d+")) {
            if (Integer.parseInt(axis1) < 0 && Integer.parseInt(axis2) < 0) {
                viewHolder.axisMidNext.setText(0+" кг");
            }
            else if (Integer.parseInt(axis1) < 0) {
                viewHolder.axisMidNext.setText((Integer.parseInt(axis1)+" кг"));
            }
            else if (Integer.parseInt(axis1) < 0) {
                viewHolder.axisMidNext.setText((Integer.parseInt(axis2)+" кг"));
            }
            else viewHolder.axisMidNext.setText((Integer.parseInt(axis1)+Integer.parseInt(axis2))+" кг");
        }


        // КОСТЫЛЬ РАЗКОМИТИТЬ!!!!!!!!

//        int axis3ColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_AXIS3);
//        String axis3 = cursor.getString(axis3ColumnIndex);
//        viewHolder.axis3.setText(axis3+" т");


        int axis4ColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_AXIS3);
        String axis4 = cursor.getString(axis4ColumnIndex);
        if (axis4 != null && axis4.matches("-?\\d+")) {
            if (Integer.parseInt(axis4) < 0) {
                viewHolder.axis4.setText(0+" кг");
            }
            else if (Integer.parseInt(axis4) > 0 ) {
//                viewHolder.axis4.setText(Integer.parseInt(axis4)+" кг");
                viewHolder.axis4.setText(a[3]+" кг");
            }
        }



        // КОСТЫЛЬ УДАЛИТЬ!!!!!!!!!!

        int axis3ColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_AXIS3);
        String axis3 = cursor.getString(axis3ColumnIndex);

        if (axis3 != null && axis3.matches("-?\\d+")) {
            axis3 = Integer.parseInt(axis3) + (int)Math.round(Math.random()*100)+"";
            if (Integer.parseInt(axis3) < 0 ) {
                viewHolder.axis3.setText(0+" кг");
            }
            else if (Integer.parseInt(axis3) > 0 && axis3.matches("-?\\d+")) {
//                viewHolder.axis3.setText(Integer.parseInt(axis3)+" кг");
                viewHolder.axis3.setText(a[2]+" кг");
            }
        }


        if (axis3 != null && axis4 != null && axis3.matches("-?\\d+") && axis4.matches("-?\\d+")) {
            if (Integer.parseInt(axis3) < 0 && Integer.parseInt(axis4) < 0) {
                viewHolder.axisMidBack.setText(0 + " кг");
            } else if (Integer.parseInt(axis3) < 0) {
                viewHolder.axisMidBack.setText((Integer.parseInt(axis3) + " кг"));
            } else if (Integer.parseInt(axis4) < 0) {
                viewHolder.axisMidBack.setText((Integer.parseInt(axis4) + " кг"));
            } else
                viewHolder.axisMidBack.setText((Integer.parseInt(axis3) + Integer.parseInt(axis4)) + " кг");
        }
    }





    // В этом методе мы привязываем данные к ячейке
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        int idColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes._ID);
        long id = cursor.getLong(idColumnIndex);

        Log.d("ID", "#ID#"+id+" "+NotesContract.Notes._ID);
        if (id == 1) {
            editAxis(viewHolder, DbActivity.getT1());
        }
        else if(id == 2){
            editAxis(viewHolder, DbActivity.getT2());
        }
        //ID ячейки списка!!!!!!!!!
        viewHolder.itemView.setTag(id);

        int titleColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_TITLE);
        String nameDriver = cursor.getString(titleColumnIndex);
        viewHolder.name_driver.setText("("+nameDriver+")");

        int noteColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_NOTE);
        String nameDeviceList = cursor.getString(noteColumnIndex);
        viewHolder.number_truck.setText(nameDeviceList);



        int numberPhoneColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_NUMBER_PHONE);
        String namberPhone = cursor.getString(numberPhoneColumnIndex);
//        viewHolder.axis4.setText(axis4);


        int dateColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_UPDATED_TS);
        long updatedTs = cursor.getLong(dateColumnIndex);
        Date date = new Date(updatedTs);

//        viewHolder.dateTv.setText(viewHolder.SDF.format(date));
    }

    // В этом методе мы создаем новую ячейку
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.view_item_note, parent, false);
        return new ViewHolder(view);
    }


//    // В этом методе мы возвращаем количество элементов списка
//    @Override
//    public int getItemCount() {
//        return count;
//    }

    /**
     * View holder
     * Хранит информацию о ячейке
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());

        private final TextView number_truck;
        private final TextView name_driver;
        private final TextView axis1;
        private final TextView axisMidNext;
        private final TextView axis2;
        private final TextView axis3;
        private final TextView axisMidBack;
        private final TextView axis4;
        private final ImageView icon_phone;
        private final ImageView button_map;
//        private final TextView dateTv;


        public ViewHolder(View itemView) {
            super(itemView);

            this.number_truck = itemView.findViewById(R.id.number_truck);
            this.name_driver = itemView.findViewById(R.id.name_driver);
            this.axis1 = itemView.findViewById(R.id.axis1);
            this.axis2 = itemView.findViewById(R.id.axis2);
            this.axis3 = itemView.findViewById(R.id.axis3);
            this.axis4 = itemView.findViewById(R.id.axis4);
            this.axisMidNext = itemView.findViewById(R.id.axis_mid_next);
            this.axisMidBack = itemView.findViewById(R.id.axis_mid_back);
            this.icon_phone = itemView.findViewById(R.id.icon_phone);
            this.button_map = itemView.findViewById(R.id.button_map);
//            this.dateTv = itemView.findViewById(R.id.date_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long noteId = (Long) v.getTag();

                    onNoteClickListener.onNoteClick(noteId);
                }
            });
            icon_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long noteId = 78788;

                    onNoteClickListenerPhone.onNoteClickPhone(noteId);
                }
            });
            button_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long noteId = 78789;

                    onNoteClickListenerMap.onNoteClickMap(noteId);
                }
            });
        }
    }

    /**
     * Слушатель для обработки кликов
     */
    public interface OnNoteClickListener {
        void onNoteClick(long noteId);
    }

    /**
     * Слушатель для обработки кликов звонка
     */
    public interface OnNoteClickListenerPhone {
        void onNoteClickPhone(long noteId);
    }

    /**
     * Слушатель для обработки кликов звонка
     */
    public interface OnNoteClickListenerMap {
        void onNoteClickMap(long noteId);
    }
}
