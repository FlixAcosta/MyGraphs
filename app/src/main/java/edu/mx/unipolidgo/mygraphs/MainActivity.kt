package edu.mx.unipolidgo.mygraphs

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import com.androidplot.xy.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.Arrays

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val domainLabels = arrayOf<Number>(1,2,3,4,5,6,7,8,9,10)
        val series1Number = arrayOf<Number>(1,4,8,10,6,7,8,3,4,5)
        val series2Number = arrayOf<Number>(2,6,7,3,5,8,9,4,2,3)

        val series1 : XYSeries = SimpleXYSeries(Arrays.asList(* series1Number),
        SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series 1")

        val series2 :XYSeries = SimpleXYSeries(Arrays.asList(* series2Number),
            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series 2")

        val series1Format = LineAndPointFormatter(Color.BLUE,Color.BLACK,null,null)
        val series2Format = LineAndPointFormatter(Color.DKGRAY,Color.LTGRAY,null,null)

        series1Format.setInterpolationParams(CatmullRomInterpolator.Params(
            10,CatmullRomInterpolator.Type.Uniform))

        series2Format.setInterpolationParams(CatmullRomInterpolator.Params(
            10,CatmullRomInterpolator.Type.Centripetal))


        plot.addSeries(series1,series1Format)
        plot.addSeries(series2,series2Format)



        plot.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format = object : Format(){
            override fun format(
                obj: Any?,
                toAppendTo: StringBuffer?,
                pos: FieldPosition?
            ): StringBuffer {
                //TODO("Not yet implemented")
                val i = Math.round((obj as Number).toFloat())
                return  toAppendTo!!.append(domainLabels[i])

            }

            override fun parseObject(source: String?, pos: ParsePosition?): Any? {
                //TODO("Not yet implemented")
                return null
            }

        }

        PanZoom.attach(plot)
    }
}