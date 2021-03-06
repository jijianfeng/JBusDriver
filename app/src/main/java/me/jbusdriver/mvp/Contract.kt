package me.jbusdriver.mvp

import com.cfzx.mvp.view.BaseView
import me.jbusdriver.mvp.bean.Genre
import me.jbusdriver.mvp.bean.Magnet
import me.jbusdriver.mvp.bean.Movie
import me.jbusdriver.mvp.bean.MovieDetail
import me.jbusdriver.mvp.presenter.BasePresenter
import me.jbusdriver.ui.data.DataSourceType
import org.jsoup.nodes.Element

/**
 * Created by Administrator on 2017/4/9.
 */
interface MainContract {
    interface MainView : BaseView
    interface MainPresenter : BasePresenter<MainView>
}

interface LinkListContract {
    interface LinkListView : BaseView.BaseListWithRefreshView {
        val type: DataSourceType
        val pageMode: Int
        fun insertDatas(pos: Int, datas: List<*>)
        fun moveTo(pos: Int)
    }

    interface LinkListPresenter : BasePresenter.BaseRefreshLoadMorePresenter<LinkListView>, BasePresenter.LazyLoaderPresenter {
        fun loadAll(iaAll: Boolean)
        fun jumpToPage(page: Int)
        fun isPrevPageLoaded(currentPage: Int): Boolean
    }
}

interface MovieDetailContract {
    interface MovieDetailView : BaseView {
        val movie: Movie
        val detailMovieFromDisk: MovieDetail?
        fun addMagnet(t: List<Magnet>)
        fun initMagnetLoad()
    }

    interface MovieDetailPresenter : BasePresenter<MovieDetailView>, BasePresenter.RefreshPresenter {
        fun loadDetail()
        fun loadMagnets(doc: Element)
    }
}

interface MovieParseContract {
    interface MovieParseView : BaseView
    interface MovieParsePresenter : BasePresenter<MovieParseView>

}

interface MineCollectContract {
    interface MineCollectView : BaseView
    interface MineCollectPresenter : BasePresenter<MineCollectView>, BasePresenter.LazyLoaderPresenter
}


interface ActressCollectContract {
    interface ActressCollectView : BaseView.BaseListWithRefreshView
    interface ActressCollectPresenter : BasePresenter.BaseRefreshLoadMorePresenter<ActressCollectView>, BasePresenter.LazyLoaderPresenter
}

interface LinkCollectContract {
    interface LinkCollectView : BaseView.BaseListWithRefreshView
    interface LinkCollectPresenter : BasePresenter.BaseRefreshLoadMorePresenter<LinkCollectView>, BasePresenter.LazyLoaderPresenter
}

interface GenrePageContract {
    interface GenrePageView : BaseView {
        val titleValues: MutableList<String>
        val fragmentValues: MutableList<List<Genre>>
    }

    interface GenrePagePresenter : BasePresenter<GenrePageView>, BasePresenter.LazyLoaderPresenter
}

interface GenreListContract {
    interface GenreListView : BaseView.BaseListWithRefreshView {
        val data: List<Genre>
    }

    interface GenreListPresenter : BasePresenter.BaseRefreshLoadMorePresenter<GenreListView>, BasePresenter.LazyLoaderPresenter
}


interface HistoryContract {
    interface HistoryView : BaseView.BaseListWithRefreshView

    interface HistoryPresenter : BasePresenter.BaseRefreshLoadMorePresenter<HistoryView>, BasePresenter.LazyLoaderPresenter {
        fun clearHistory()
    }
}
