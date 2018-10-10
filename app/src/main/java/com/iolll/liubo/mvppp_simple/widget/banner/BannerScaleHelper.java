package com.iolll.liubo.mvppp_simple.widget.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.iolll.liubo.mvppp_simple.utils.Timers;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.iolll.liubo.mvppp_simple.utils.DensityUtils.dp2px;


/**
 * Created by LiuBo on 2018/9/26.
 */
public class BannerScaleHelper {
    private BannerRecyclerView mRecyclerView;
    private Context mContext;

    private float mScale = 0.8f; // 两边视图scale
    private int mPagePadding = BannerAdapterHelper.sPagePadding; // 卡片的padding, 卡片间的距离等于2倍的mPagePadding
    private int mShowLeftCardWidth = BannerAdapterHelper.sShowLeftCardWidth;   // 左边卡片显示大小

    private int mCardWidth; // 卡片宽度
    private int mOnePageWidth; // 滑动一页的距离
    private int mCardGalleryWidth;

    private int mFirstItemPos;
    private int mCurrentItemOffset;
    private int oneTime = 3000; //毫秒
    private CardLinearSnapHelper mLinearSnapHelper = new CardLinearSnapHelper();
    private int mLastPos;
    private boolean isTouching;//是否在触摸
    private boolean isScrolling;
    private boolean isMaxBanner = false;

    public boolean isMaxBanner() {
        return isMaxBanner;
    }

    public void setMaxBanner(boolean maxBanner) {
        isMaxBanner = maxBanner;
    }

    public void attachToRecyclerView(final BannerRecyclerView mRecyclerView) {
        if (mRecyclerView == null) {
            return;
        }
        this.mRecyclerView = mRecyclerView;
        mContext = mRecyclerView.getContext();
        mRecyclerView.setTouchEventListener(new BannerRecyclerView.OnTouchEventListener() {
            @Override
            public void OnTouchEvent(MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_UP) {
                    isTouching = false;
                } else {
                    isTouching = true;
                }
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // Log.e("TAG", "RecyclerView.OnScrollListener onScrollStateChanged");
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mLinearSnapHelper.mNoNeedToScroll = getCurrentItem() == 0 ||
                            getCurrentItem() == mRecyclerView.getAdapter().getItemCount() - 2;
                    if (mLinearSnapHelper.finalSnapDistance[0] == 0
                            && mLinearSnapHelper.finalSnapDistance[1] == 0) {
                        mCurrentItemOffset = 0;
                        mLastPos = getCurrentItem();
                        //认为是一次滑动停止 这里可以写滑动停止回调
                        mRecyclerView.dispatchOnPageSelected(mLastPos);
                        //Log.e("TAG", "滑动停止后最终位置为" + getCurrentItem());
                    }
                } else {
                    mLinearSnapHelper.mNoNeedToScroll = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //Log.e("TAG", String.format("onScrolled dx=%s, dy=%s", dx, dy));
                super.onScrolled(recyclerView, dx, dy);
                // dx>0则表示右滑, dx<0表示左滑, dy<0表示上滑, dy>0表示下滑
                mCurrentItemOffset += dx;
                onScrolledChangedCallback();
            }
        });
        initWidth();
        mLinearSnapHelper.attachToRecyclerView(mRecyclerView);
    }

    /**
     * 初始化卡片宽度
     */
    private void initWidth() {
        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mRecyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mCardGalleryWidth = mRecyclerView.getWidth();
                mCardWidth = mCardGalleryWidth - dp2px(mContext, 2 * (mPagePadding + mShowLeftCardWidth));
                mOnePageWidth = mCardWidth;
                scrollToPosition(mFirstItemPos);
            }
        });
    }

    public void setCurrentItem(int item) {
        setCurrentItem(item, false);
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        if (mRecyclerView == null) {
            return;
        }
        if (smoothScroll) {
            mRecyclerView.smoothScrollToPosition(item);
        } else {
            scrollToPosition(item);
        }
    }

    private Disposable timer;

    public void initTimer() {
        if (isMaxBanner) {
            if (null != timer)
                timer.dispose();
            timer = Timers.forever(oneTime).subscribe(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) throws Exception {
                    if (!isTouching && !mLinearSnapHelper.mNoNeedToScroll && integer != 0) {
                        mRecyclerView.smoothScrollToPosition(getCurrentItem() + 1);
                    }
                }
            });
        }
    }

    public void scrollToPosition(int pos) {
        if (mRecyclerView == null) {
            return;
        }
        //mRecyclerView.getLayoutManager()).scrollToPositionWithOffset 方法不会回调  RecyclerView.OnScrollListener 的onScrollStateChanged方法,是瞬间跳到指定位置
        //mRecyclerView.smoothScrollToPosition 方法会回调  RecyclerView.OnScrollListener 的onScrollStateChanged方法 并且是自动居中，有滚动过程的滑动到指定位置
        ((LinearLayoutManager) mRecyclerView.getLayoutManager()).
                scrollToPositionWithOffset(pos,
                        dp2px(mContext, mPagePadding + mShowLeftCardWidth));
        mCurrentItemOffset = 0;
        mLastPos = pos;
        //认为是一次滑动停止 这里可以写滑动停止回调
        mRecyclerView.dispatchOnPageSelected(mLastPos);
        initTimer();//滑动停止重新初始化计时器
        //onScrolledChangedCallback();
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                onScrolledChangedCallback();
            }
        });
    }

    public void setFirstItemPos(int firstItemPos) {
        if (isMaxBanner) {
            this.mFirstItemPos = firstItemPos;
            scrollToPosition(mFirstItemPos);
        }else{
            if (firstItemPos>=mRecyclerView.getAdapter().getItemCount()){
                Log.e(TAG, "setFirstItemPos: firstItemPos Must < mRecyclerView.getAdapter().getItemCount()",null );
            }else{
                this.mFirstItemPos = firstItemPos;
                scrollToPosition(mFirstItemPos);
            }
        }
    }

    private static final String TAG = "BannerScaleHelper";
    /**
     * RecyclerView位移事件监听, view大小随位移事件变化
     */
    private void onScrolledChangedCallback() {
        if (mOnePageWidth == 0) {
            return;
        }
        int currentItemPos = getCurrentItem();
        int offset = mCurrentItemOffset - (currentItemPos - mLastPos) * mOnePageWidth;
        float percent = (float) Math.max(Math.abs(offset) * 1.0 / mOnePageWidth, 0.0001);

        //Log.e("TAG",String.format("offset=%s, percent=%s", offset, percent));
        View leftView = null;
        View currentView;
        View rightView = null;
        if (currentItemPos > 0) {
            leftView = mRecyclerView.getLayoutManager().findViewByPosition(currentItemPos - 1);
        }
        currentView = mRecyclerView.getLayoutManager().findViewByPosition(currentItemPos);
        if (currentItemPos < mRecyclerView.getAdapter().getItemCount() - 1) {
            rightView = mRecyclerView.getLayoutManager().findViewByPosition(currentItemPos + 1);
        }

        if (leftView != null) {
            // y = (1 - mScale)x + mScale
            leftView.setScaleY((1 - mScale) * percent + mScale);
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) currentView.getLayoutParams();
            setViewMargin(currentView,layoutParams.rightMargin,layoutParams.topMargin,layoutParams.rightMargin,layoutParams.bottomMargin);
        }else{
            if (getCurrentItem()==0) {
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) currentView.getLayoutParams();
                setViewMargin(currentView, layoutParams.rightMargin * 4, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
            }
        }
        if (currentView != null) {
            // y = (mScale - 1)x + 1
            currentView.setScaleY((mScale - 1) * percent + 1);
        }
        if (rightView != null) {
            // y = (1 - mScale)x + mScale
            rightView.setScaleY((1 - mScale) * percent + mScale);
        }
    }

    public int getCurrentItem() {
        //return ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findLastVisibleItemPosition() - 1;
        View view = mLinearSnapHelper.findSnapView(mRecyclerView.getLayoutManager());
        if (null == view)
            return 0;
        return mRecyclerView.getLayoutManager().getPosition(view);
    }

    public void setScale(float scale) {
        mScale = scale;
    }

    public void setPagePadding(int pagePadding) {
        mPagePadding = pagePadding;
    }

    public void setShowLeftCardWidth(int showLeftCardWidth) {
        mShowLeftCardWidth = showLeftCardWidth;
    }

    /**
     * 防止卡片在第一页和最后一页因无法"居中"而一直循环调用onScrollStateChanged-->SnapHelper.snapToTargetExistingView-->onScrollStateChanged
     * Created by jameson on 9/3/16.
     */
    private static class CardLinearSnapHelper extends LinearSnapHelper {
        public boolean mNoNeedToScroll = false;
        public int[] finalSnapDistance = {0, 0};

        @Override
        public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
            //Log.e("TAG", "calculateDistanceToFinalSnap");
            if (mNoNeedToScroll) {
                finalSnapDistance[0] = 0;
                finalSnapDistance[1] = 0;
            } else {
                finalSnapDistance = super.calculateDistanceToFinalSnap(layoutManager, targetView);
            }
            return finalSnapDistance;
        }
    }
    private void setViewMargin(View view, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (lp.leftMargin != left || lp.topMargin != top || lp.rightMargin != right || lp.bottomMargin != bottom) {
            lp.setMargins(left, top, right, bottom);
            view.setLayoutParams(lp);
        }
    }
}
