package com.tetuo41.arnovel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.tetuo41.arnovel.common.CommonDef;
import com.tetuo41.arnovel.common.CommonUtil;

/**
* カメラプレビュー画面を表示するクラスです。
* @author　HackathonG
* @version 1.0
*/
public class CameraPreviewActivity extends Activity implements View.OnClickListener{

	/** カレントパッケージ名 */
	private static final String CURRENT_PACKAGE =
			StageIntroActivity.class.getPackage().getName();

	/** 共通クラスオブジェクト */
	private CommonUtil cmnutil;
	private CommonDef cmndef;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // setContentView(new CameraPreview(this));
        setContentView(R.layout.camera_preview);

		// ClickListener登録
		findViewById(R.id.shutter).setOnClickListener(this);
		findViewById(R.id.stage_select_back).setOnClickListener(this);
	}

    /**
     * コンストラクタ
     */
    public CameraPreviewActivity() {
    	cmnutil = new CommonUtil();
    	cmndef = new CommonDef();
    }

    /**
     * ボタンクリック時の処理を記述する。
     * @param View ボタンオブジェクト
     */
    public void onClick(View v) {

    	switch (v.getId()) {
		case R.id.shutter:
			// シャッターボタンクリック時
			ShutterClick();

			break;
		case R.id.stage_select_back:
			// ステージセレクトバックボタンクリック時
			StageSelectBackClick();

			break;
		default:
			break;
		}
	}

    /**
     * シャッターボタンクリック時の処理を記述する。
     */
    private void ShutterClick() {

    	try {
    		// シャッターボタンクリック時、ステージ導入画面へ遷移
    		Intent intent = new Intent();
    		intent.setClassName(CURRENT_PACKAGE, CURRENT_PACKAGE + ".StageIntroActivity");
    		startActivity(intent);

    	} catch (RuntimeException e) {
    		// ステージ導入画面へ遷移できなかった場合
    		Log.e("ERROR", e.toString());

    		// アラートダイアログで警告を表示
    		AlertDialog.Builder adb = new AlertDialog.Builder(this);
    		adb.setTitle("エラー");
    		adb.setMessage(cmndef.CAMERA_PREVIEW_ERROR_MSG1);
    		adb.setPositiveButton("OK",
    	            new DialogInterface.OnClickListener() {
    	                public void onClick(DialogInterface dialog, int which) {
    	                	// 処理なし
    	                }
    	            });
    		adb.show();

    		// 処理を終了する
    		return;
    	}
    }

    /**
     * ステージセレクトバックボタンクリック時の処理を記述する。
     */
    private void StageSelectBackClick() {

    	try {
    		// ステージセレクトバックボタンクリック時、ステージセレクト画面へ遷移
    		Intent intent = new Intent();
    		intent.setClassName(CURRENT_PACKAGE, CURRENT_PACKAGE + ".StageSelectActivity");
    		startActivity(intent);

    	} catch (RuntimeException e) {
    		// ステージセレクト画面へ遷移できなかった場合
    		Log.e("ERROR", e.toString());

    		// アラートダイアログで警告を表示
    		AlertDialog.Builder adb = new AlertDialog.Builder(this);
    		adb.setTitle("エラー");
    		adb.setMessage(cmndef.TOP_ERROR_MSG1);
    		adb.setPositiveButton("OK",
    	            new DialogInterface.OnClickListener() {
    	                public void onClick(DialogInterface dialog, int which) {
    	                	// 処理なし
    	                }
    	            });
    		adb.show();

    		// 処理を終了する
    		return;
    	}
    }

}
