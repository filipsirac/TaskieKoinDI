package hr.ferit.brunozoric.taskie.ui.fragments.updatetask

import hr.ferit.brunozoric.taskie.model.BackendTask

interface UpdateTaskDialogContract {

    interface View{
        fun onUpdateSuccessful(backendTask: BackendTask)

        fun onFailure(error: String)
    }

    interface Presenter{
        fun setView(view: UpdateTaskDialogContract.View)

        fun editTask(currentID: String, title: String, description: String, priority: Int)
    }
}