package hr.ferit.brunozoric.taskie.ui.fragments.taskdetails

import hr.ferit.brunozoric.taskie.model.BackendTask
import retrofit2.Response

interface TaskDetailsContract {

    interface View{
        fun onUpdateSuccessful(response: BackendTask)

        fun onFailure(error: String)
    }

    interface Presenter{
        fun setView(view: View)

        fun getTask(id: String)
    }
}