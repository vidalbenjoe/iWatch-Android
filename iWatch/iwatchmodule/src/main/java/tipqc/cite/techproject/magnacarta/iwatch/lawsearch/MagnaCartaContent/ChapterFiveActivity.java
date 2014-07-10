package tipqc.cite.techproject.magnacarta.iwatch.lawsearch.MagnaCartaContent;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import tipqc.cite.techproject.magnacarta.R;
import tipqc.cite.techproject.magnacarta.libraries.ui.pagecurl.CurlPage;
import tipqc.cite.techproject.magnacarta.libraries.ui.pagecurl.CurlView;

/**
 * Simple Activity for curl testing.
 *
 * @author harism
 */
public class ChapterFiveActivity extends Activity  {

    private CurlView mCurlView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chaptertwo_curl_view);

        int index = 0;
        if (getLastNonConfigurationInstance() != null) {
            index = (Integer) getLastNonConfigurationInstance();
        }
        mCurlView = (CurlView) findViewById(R.id.curl);
        mCurlView.setPageProvider(new PageProvider());
        mCurlView.setSizeChangedObserver(new SizeChangedObserver());
        mCurlView.setCurrentIndex(index);
        mCurlView.setBackgroundColor(0xFF202830);

        // This is something somewhat experimental. Before uncommenting next
        // line, please see method comments in CurlView.
        // mCurlView.setEnableTouchPressure(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        mCurlView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCurlView.onResume();
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        return mCurlView.getCurrentIndex();
    }

    /**
     * Bitmap provider.
     */
    private class PageProvider implements CurlView.PageProvider {

        // Bitmap resources.
        private int[] mBitmapIds = {
                R.drawable.chapterfivetitle, R.drawable.chapterfivepageone,
                R.drawable.chapterfivepagetwo,R.drawable.chapterfivepagethree,
                R.drawable.chapterfivepagefive, R.drawable.chapterfivepagesix,
                R.drawable.chapterfivepageseven, R.drawable.chapterfivepageeight,
                R.drawable.chapterfivepagenine, R.drawable.chapterfivepageten,
                R.drawable.chapterfivepageeleven, R.drawable.chapterfivepagetwelve,
                R.drawable.chapterfivepagethirteen,R.drawable.chapterfivepagefourteen,
                R.drawable.chapterfivepagefifthteen

        };

        @Override
        public int getPageCount() {
            return 16;
        }

        private Bitmap loadBitmap(int width, int height, int index) {
            Bitmap b = Bitmap.createBitmap(width, height,
                    Bitmap.Config.ARGB_8888);
            b.eraseColor(0xFFFFFFFF);
            Canvas c = new Canvas(b);
            Drawable d = getResources().getDrawable(mBitmapIds[index]);

            int margin = 8;
            int border = 3;
            Rect r = new Rect(margin, margin, width - margin, height - margin);

            int imageWidth = r.width() - (border * 2);
            int imageHeight = imageWidth * d.getIntrinsicHeight()
                    / d.getIntrinsicWidth();
            if (imageHeight > r.height() - (border * 2)) {
                imageHeight = r.height() - (border * 2);
                imageWidth = imageHeight * d.getIntrinsicWidth()
                        / d.getIntrinsicHeight();
            }

            r.left += ((r.width() - imageWidth) / 2) - border;
            r.right = r.left + imageWidth + border + border;
            r.top += ((r.height() - imageHeight) / 2) - border;
            r.bottom = r.top + imageHeight + border + border;

            Paint p = new Paint();
            p.setColor(0xFFC0C0C0);
            c.drawRect(r, p);
            r.left += border;
            r.right -= border;
            r.top += border;
            r.bottom -= border;

            d.setBounds(r);
            d.draw(c);

            return b;
        }

        @Override
        public void updatePage(CurlPage page, int width, int height, int index) {

            switch (index) {
                // First case is image on front side, solid colored back.
                case 0: {
                    Bitmap front = loadBitmap(width, height, 0);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setColor(Color.rgb(236, 128, 227), CurlPage.SIDE_BACK);
                    break;
                }
                // Second case is image on back side, solid colored front.
                case 1: {
                 /*   Bitmap back = loadBitmap(width, height, 2);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    page.setColor(Color.rgb(127, 140, 180), CurlPage.SIDE_FRONT);*/

                    Bitmap front = loadBitmap(width, height, 1);
                    Bitmap back = loadBitmap(width, height, 1);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    break;
                }
                // Third case is images on both sides.
                case 2: {
                    Bitmap front = loadBitmap(width, height, 2);
                    Bitmap back = loadBitmap(width, height, 2);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    break;
                }
                // Fourth case is images on both sides - plus they are blend against
                // separate colors.
                case 3: {
                    Bitmap front = loadBitmap(width, height, 3);
                    Bitmap back = loadBitmap(width, height, 3);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    break;
                }
                // Fifth case is same image is assigned to front and back. In this
                // scenario only one texture is used and shared for both sides.



                case 4: {
                    Bitmap front = loadBitmap(width, height, 4);
                    Bitmap back = loadBitmap(width, height, 4);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    break;
                }

                case 5: {
                    Bitmap front = loadBitmap(width, height, 5);
                    Bitmap back = loadBitmap(width, height, 5);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    break;
                }


                case 6: {
                    Bitmap front = loadBitmap(width, height, 6);
                    Bitmap back = loadBitmap(width, height, 6);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    break;
                }

                case 7: {
                    Bitmap front = loadBitmap(width, height, 7);
                    Bitmap back = loadBitmap(width, height, 7);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    break;
                }

                case 8: {
                    Bitmap front = loadBitmap(width, height, 8);
                    Bitmap back = loadBitmap(width, height, 8);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    break;
                }


                case 9: {
                    Bitmap front = loadBitmap(width, height, 9);
                    Bitmap back = loadBitmap(width, height, 9);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    break;
                }


                case 10: {
                    Bitmap front = loadBitmap(width, height, 10);
                    Bitmap back = loadBitmap(width, height, 10);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    break;
                }


                case 11: {
                    Bitmap front = loadBitmap(width, height, 11);
                    Bitmap back = loadBitmap(width, height, 11);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    break;
                }


                case 12: {
                    Bitmap front = loadBitmap(width, height, 12);
                    Bitmap back = loadBitmap(width, height, 12);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    break;
                }

                case 13: {
                    Bitmap front = loadBitmap(width, height, 13);
                    Bitmap back = loadBitmap(width, height, 13);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    break;
                }

                case 14: {
                    Bitmap front = loadBitmap(width, height, 14);
                    Bitmap back = loadBitmap(width, height, 14);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    break;
                }

                case 15: {
                    Bitmap front = loadBitmap(width, height, 15);
                    Bitmap back = loadBitmap(width, height, 15);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    break;
                }

                case 16:{
                    Bitmap front = loadBitmap(width, height, 16);

                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setColor(Color.rgb(236, 128, 227), CurlPage.SIDE_BACK);

                    break;
                }

                case 17:{
                    Bitmap front = loadBitmap(width, height, 17);

                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setColor(Color.rgb(236, 128, 227), CurlPage.SIDE_BACK);

                    break;
                }


            }
        }

    }

    /**
     * CurlView size changed observer.
     */
    private class SizeChangedObserver implements CurlView.SizeChangedObserver {
        @Override
        public void onSizeChanged(int w, int h) {
            if (w > h) {
                mCurlView.setViewMode(CurlView.SHOW_TWO_PAGES);
                mCurlView.setMargins(.1f, .05f, .1f, .05f);
            } else {
                mCurlView.setViewMode(CurlView.SHOW_ONE_PAGE);
                mCurlView.setMargins(.1f, .1f, .1f, .1f);
            }
        }
    }

}