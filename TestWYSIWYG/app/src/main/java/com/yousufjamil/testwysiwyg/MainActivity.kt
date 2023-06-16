package com.yousufjamil.testwysiwyg

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import jp.wasabeef.richeditor.RichEditor


//import com.onegravity.rteditor.RTEditText
//import com.onegravity.rteditor.RTManager
//import com.onegravity.rteditor.api.RTApi
//import com.onegravity.rteditor.api.RTMediaFactoryImpl
//import com.onegravity.rteditor.api.RTProxyImpl
//import com.onegravity.rteditor.api.format.RTFormat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val rtEditText: RTEditText = findViewById(R.id.rtEditText)
//
//        rtEditText.setRichTextEditing(true, "Test")
//
//        val currentTest = rtEditText.getRichText(RTFormat.HTML)

        // initialize rich text manager
        // initialize rich text manager
//        val rtApi = RTApi(this, RTProxyImpl(this), RTMediaFactoryImpl(this, true))
//        mRTManager = RTManager(rtApi, savedInstanceState)

        val mEditor: RichEditor = findViewById(R.id.editor)
        val mPreview = findViewById<TextView>(R.id.preview)

        var returned: Any = ""
//        val scrollView = findViewById<ScrollView>(R.id.scrollView2)

        mEditor.setEditorHeight(100);
        mEditor.setEditorFontSize(15);
        mEditor.setPadding(10, 10, 10, 10)
        mEditor.setPlaceholder("Type text here...")

        mEditor.setOnTextChangeListener {
            text -> mPreview.text = text
            mEditor.scrollY = View.FOCUS_DOWN
//            scrollView.smoothScrollTo(0, mEditor.bottom)
        }

        findViewById<View>(R.id.action_undo).setOnClickListener { mEditor.undo() }

        findViewById<View>(R.id.action_redo).setOnClickListener { mEditor.redo() }

        findViewById<View>(R.id.action_bold).setOnClickListener { mEditor.setBold() }

        findViewById<View>(R.id.action_italic).setOnClickListener { mEditor.setItalic() }

        findViewById<View>(R.id.action_subscript).setOnClickListener { mEditor.setSubscript() }

        findViewById<View>(R.id.action_superscript).setOnClickListener { mEditor.setSuperscript() }

        findViewById<View>(R.id.action_strikethrough).setOnClickListener { mEditor.setStrikeThrough() }

        findViewById<View>(R.id.action_underline).setOnClickListener { mEditor.setUnderline() }

        findViewById<View>(R.id.action_heading1).setOnClickListener { mEditor.setHeading(1) }

        findViewById<View>(R.id.action_heading2).setOnClickListener { mEditor.setHeading(2) }

        findViewById<View>(R.id.action_heading3).setOnClickListener { mEditor.setHeading(3) }

        findViewById<View>(R.id.action_heading4).setOnClickListener { mEditor.setHeading(4) }

        findViewById<View>(R.id.action_heading5).setOnClickListener { mEditor.setHeading(5) }

        findViewById<View>(R.id.action_heading6).setOnClickListener { mEditor.setHeading(6) }

        findViewById<View>(R.id.action_txt_color).setOnClickListener(object : View.OnClickListener {
            private var isChanged = false
            override fun onClick(v: View) {
                mEditor.setTextColor(if (isChanged) Color.BLUE else Color.BLACK)
                isChanged = !isChanged
            }
        })

        findViewById<View>(R.id.action_bg_color).setOnClickListener(object : View.OnClickListener {
            private var isChanged = false
            override fun onClick(v: View) {
                mEditor.setTextBackgroundColor(if (isChanged) Color.TRANSPARENT else Color.YELLOW)
                isChanged = !isChanged
            }
        })

        findViewById<ImageButton>(R.id.action_indent).setOnClickListener { mEditor.setIndent() }

        findViewById<View>(R.id.action_outdent).setOnClickListener { mEditor.setOutdent() }

        findViewById<View>(R.id.action_align_left).setOnClickListener { mEditor.setAlignLeft() }

        findViewById<View>(R.id.action_align_center).setOnClickListener { mEditor.setAlignCenter() }

        findViewById<View>(R.id.action_align_right).setOnClickListener { mEditor.setAlignRight() }

        findViewById<View>(R.id.action_blockquote).setOnClickListener { mEditor.setBlockquote() }

        findViewById<View>(R.id.action_insert_bullets).setOnClickListener { mEditor.setBullets() }

        findViewById<View>(R.id.action_insert_numbers).setOnClickListener { mEditor.setNumbers() }

        findViewById<View>(R.id.action_insert_image).setOnClickListener {
            mEditor.insertImage(
                "https://home.acc-web.repl.co/algo.png",
                "Logo", 320
            )
        }

        findViewById<View>(R.id.action_insert_link).setOnClickListener {
            showPopup("AddLink")
        }

        findViewById<View>(R.id.action_insert_checkbox).setOnClickListener { mEditor.insertTodo() }

        findViewById<View>(R.id.action_change_font_size).setOnClickListener {
            showPopup("FontSize")
        }
    }

    fun showPopup(type: String) {
        var data: Any = ""

        val builder = AlertDialog.Builder(this)
        val inflator = layoutInflater
        val inputLayout = inflator.inflate(R.layout.popup_et, null)
        val input = inputLayout.findViewById<EditText>(R.id.editTextText)

        when (type) {
            "FontSize" -> {
                builder.setTitle("Set Font Size")
                input.inputType = InputType.TYPE_CLASS_NUMBER
                input.hint = "Enter a font size from 1 to 7..."
            }
            "AddLink" -> {
                builder.setTitle("Enter link")
                input.inputType = InputType.TYPE_CLASS_TEXT
                input.hint = "Enter the link..."
            }
            else -> Toast.makeText(this@MainActivity, "Unknown Error", Toast.LENGTH_SHORT).show()
        }

        input.maxLines = 1

        builder.setPositiveButton("OK") { dialog, which ->
            data = input.text.toString()
            when (type) {
                "FontSize" -> {
                    if (data != "" && data.toString().toInt() >= 0 && data.toString().toInt() <= 7) {
                        setPopup(type, data, false)
                    } else if (data.toString().toInt() < 0 || data.toString().toInt() > 7) {
                        Toast.makeText(this@MainActivity, "Font size should be a value from 1 and 7 only", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Invalid value entered", Toast.LENGTH_SHORT).show()
                    }
                }
                "AddLink" -> {
                    val dataArray = mutableListOf<String>()
                    dataArray.add(data.toString())
                    setPopup(type, data, false)
                }
                else -> Toast.makeText(this@MainActivity, "Unknown Error", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }
        builder.setView(inputLayout)
        builder.show()
    }

    fun setPopup(type: String, data: Any, repeat: Boolean) {
        val mEditor: RichEditor = findViewById(R.id.editor)
        when (type) {
            "FontSize" -> {
                val datamodified = data.toString().toInt()
                mEditor.setFontSize(datamodified)
            }
            "AddLink" -> {
                mEditor.insertLink(
                    "https://home.acc-web.repl.co/",
                    "Accorm"
                )
            }
            else -> Toast.makeText(this@MainActivity, "Unknown Error", Toast.LENGTH_SHORT).show()
        }
    }
}