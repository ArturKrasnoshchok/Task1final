package krasnoshchok.task1.auth


import android.content.Context

import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint

import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import krasnoshchok.task1.R
import kotlin.properties.Delegates


class ButtonGoogle @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0, defStyleRes: Int = 0
) : View(context, attributeSet, defStyleAttr, defStyleRes) {

    private var textColor by Delegates.notNull<Int>()
    private var background by Delegates.notNull<Int>()
    private var cornerRadius: Float by Delegates.notNull<Float>()
    private lateinit var paintText: Paint
    private lateinit var paintButton: Paint
    private val googleIcon = ResourcesCompat.getDrawable(resources, R.drawable.google, null)
    private val textSize = 16f
    private val heightButton = 40f


    data class Position(
        val textStartPositionX: Float,
        val textStartPositionY: Float,
        val googleLeftPadding: Int,
        val googleRightPadding: Int,
        val googleTopPadding: Int,
        val googleBottomPadding: Int,
    )

    init {
        if (attributeSet != null) {
            initAttributes(attributeSet, defStyleAttr, defStyleRes)
            initPaints()
        }
    }

    private fun initPaints() {
        paintText = Paint(Paint.ANTI_ALIAS_FLAG)
        paintText.color = textColor
        paintText.textSize = context.resources.getDimension(R.dimen.text_size_medium)

        paintButton = Paint(Paint.ANTI_ALIAS_FLAG)
        paintButton.color = background


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val minHeight = suggestedMinimumHeight + paddingTop + paddingBottom
        setMeasuredDimension(resolveSize(minWidth, widthMeasureSpec), resolveSize(minHeight, heightMeasureSpec))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawButton(canvas)
    }

    private fun drawButton(canvas: Canvas) {

        val position = setPosition()

        canvas.drawRoundRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            cornerRadius,
            cornerRadius,
            paintButton
        )
        //  canvas.drawPaint(paintButton)
        canvas.drawText("GOOGLE", position.textStartPositionX, position.textStartPositionY, paintText)
        googleIcon?.setBounds(position.googleLeftPadding, position.googleTopPadding,
            position.googleRightPadding, position.googleBottomPadding)
        googleIcon?.draw(canvas)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val safeWidth = w - paddingLeft - paddingRight
        val safeHeightSpan = h - paddingTop - paddingBottom
    }

    private fun setPosition(): Position {

        val centerX = width / 2f
        val centerY = height / 2f
        return Position(
            textStartPositionX = centerX - paintText.measureText("GOOGLE") / 2,
            textStartPositionY = centerY + textSize,
            googleLeftPadding = (centerX - paintText.measureText("GOOGLE") / 2 - 60).toInt(),
            googleRightPadding = (centerX - paintText.measureText("GOOGLE") / 2 - 20).toInt(),
            googleTopPadding = (centerY + textSize - heightButton).toInt(),
            googleBottomPadding = (centerY + textSize + textSize / 2).toInt()
        )

    }

    private fun initAttributes(
        attributeSet: AttributeSet,
        defStyleAttr: Int, defStyleRes: Int
    ) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ButtonGoogle, defStyleAttr, defStyleRes)
        textColor = typedArray.getColor(R.styleable.ButtonGoogle_textColor, context.getColor(R.color.black))
        background = typedArray.getColor(R.styleable.ButtonGoogle_backgroundColor, context.getColor(R.color.white))
        cornerRadius = typedArray.getDimension(R.styleable.ButtonGoogle_cornerRadius, context.resources.getDimension(R.dimen.corner_radius))
        typedArray.recycle()
    }
}