package my.demo.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import my.demo.greendao.db.DBManager;
import my.demo.greendao.entity.User;

/**
 * 加密没做，要是做缓存，加密做不做都行
 * 表结构更新后，没有做原始数据迁移，要升级数据库版本，否则会报错，数据库版本升级后之前的数据会肯定也没有了，
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.age_et)
    EditText ageEt;
    @BindView(R.id.add_btn)
    Button addBtn;
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.search_btn)
    Button searchBtn;
    @BindView(R.id.delete_et)
    EditText deleteEt;
    @BindView(R.id.delete_btn)
    Button deleteBtn;
    @BindView(R.id.search_all_btn)
    Button searchAllBtn;

    private DBManager greenDaoHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        greenDaoHelper= new DBManager(this);
    }


    @OnClick({R.id.add_btn, R.id.search_btn, R.id.delete_btn, R.id.search_all_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            //添加
            case R.id.add_btn:
                User user = new User();
                //user.setId(Long.valueOf(8)); //id是表中自增主键
                user.setName(nameEt.getText().toString());
                user.setAge(Integer.parseInt(ageEt.getText().toString()));
                try{
                    greenDaoHelper.insertUser(user);
                }catch (RuntimeException e){
                    Log.e(TAG, "Exception: "  + e);
                }

                break;

                //查询
            case R.id.search_btn:
                List<User> userList = greenDaoHelper.getDataByUserAge(Long.parseLong(searchEt.getText().toString()));
                if(null == userList || userList.size() < 1){
                    Log.e(TAG, "数据库没有该条数据" );
                }
                assert userList != null;
                for (User user1:userList){
                    Log.e(TAG, "userId: " + user1.getId() );
                    Log.e(TAG, "userName: " + user1.getName() );
                    Log.e(TAG, "userAge: " + user1.getAge() );
                }

//                User sUser = greenDaoHelper.getDataByUserId(Long.parseLong(searchEt.getText().toString()));
//                if(null == sUser){
//                    Log.e(TAG, "数据库没有该条数据" );
//                    return;
//                }
//                Log.e(TAG, "userId: " + sUser.getId() );
//                Log.e(TAG, "userName: " + sUser.getName() );
//                Log.e(TAG, "userAge: " + sUser.getAge() );
                break;

                //删除
            case R.id.delete_btn:
                try {
                    greenDaoHelper.deleteUserById(Long.parseLong(deleteEt.getText().toString()));
                }catch (RuntimeException e){
                    Log.e(TAG, "RuntimeException: " + e );
                }

                break;

                //查询全部
            case R.id.search_all_btn:
                for(User user1 :greenDaoHelper.getUserAll()){
                    Log.e(TAG, "userId: " + user1.getId() + " userName: " + user1.getName() );
                }
                break;
        }
    }
}
