## Activity
  - được hiểu như là 1 màn hình full của app bao gồm giao diện các tương tác với người dùng, một app có thể có nhiều activity và các activity có thể truyền qua lại nhau
  - một task là tập hợp các activity mà người dùng có thể tương tác khi thực hiện 1 hành động nào đó, chúng được sắp xếp theo thứ thự first in last out được sx trong 1 stack
## Vòng đời 1 activity
  - 1 acticity nhìn chung có 2 trạng thái là Running, Paused và Stopped. Running khi activity ở foreground. Paused khi mà activity không còn ở foreground (có thể bị che bởi 1 activity khác). Stopped khi activity bị che hoàn toàn bởi activity khác
  - onCreated() : gọi đến khi act được khởi tạo
  - onStart() : gọi đến khi act được nhìn thấy bởi người dùng
  - onResume(): gọi đến khi act tương tác với người dùng
  - onPause() : tại đây act dừng tương tác không nhận hay gửi thông tin khi đó act trước đó trong stack được resume
  - onStop() : gọi khi act không còn được nhìn thấy bởi người dùng
  - onDestroy(): gọi đến khi act bị tiêu hủy bởi hệ thống
  - onRestart(): gọi đến khi act được tải khởi động sau khi Stopped
|_> entire lifetime bao gồm onCreat và onDestroy, Visible Time bao gồm onStart và onStop. Foregroung lifetime là khi act tương tác với người dùng

## Navigating 
# giữa 2 activity
''' kotlin
    val intent = Intent(this, Activity2::class.java)
    startActivity(intent)
'''

# giữa 2 activity có sự truyền gửi dữ liệu
  - để gửi dữ liệu cần sử dụng startActivityForResult(). Kết quả được trả về thông qua onActivityResult, activity con dùng setResult để truyền dữ liệu cho act cha. Truyển dữ liệu bằng putExtra còn nhận bằng get...Extra
''' kotlin
    // start Activity2 để lấy kq
    val intent = Intent(this, Activity2::class.java)
    startActivityForResult(intent, myRequestCode)
    // kết quả được lấy về
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (myRequestCode==requestCode && RESULT_OK==resultCode) run {
            val urlReceive: String? = data?.getStringExtra("URL")
            txView.text=urlReceive
        }
    }
    // tại Activity2 dữ liệu được gửi đi
    val intent = Intent(this, MainActivity::class.java)
    intent.putExtra("URL",edText.text.toString())
    setResult(RESULT_OK, intent)
    finish()
'''

# lưu giữ liệu
  - khi tương tác giữa các activity việc tái tạo lại activity -> mất thông tin. Lưu trữ giữ liệu bằng onSaveInstanceState để lưu data vào Bundle. Và phục hổi data bằng onSaveInstanceState() và dữ liệu đã lưu trong Bundle)

''' kotlin
    override fun onSaveInstanceState(outState : Bundle){
        super.onSaveInstanceState(outState)
        outState.putString("URL",txView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val data= savedInstanceState.getString("URL")
        txView.text=data
    }
'''

# Intent 
  - là cái để yêu cầu hành động từ các ứng dụng khác
  - Explicit intent : biết rõ ràng địa chỉ của app
  - Implicit intent : đưa ra 1 hành động hệ thống sẽ tự tìm ứng dụng phù hợp để mở



