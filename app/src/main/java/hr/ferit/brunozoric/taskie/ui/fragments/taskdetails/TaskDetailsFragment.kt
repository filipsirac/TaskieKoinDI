package hr.ferit.brunozoric.taskie.ui.fragments.taskdetails

import android.os.Bundle
import android.view.View
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.Taskie
import hr.ferit.brunozoric.taskie.common.EXTRA_TASK_ID
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.PriorityColor
import hr.ferit.brunozoric.taskie.presentation.TaskDetailsPresenter
import hr.ferit.brunozoric.taskie.ui.fragments.updatetask.UpdateTaskFragmentDialog
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_details.*
import org.koin.android.ext.android.inject
import retrofit2.Response

class TaskDetailsFragment : BaseFragment(),
    UpdateTaskFragmentDialog.TaskUpdatedListener, TaskDetailsContract.View {

    private var taskID = NO_TASK

    private val presenter: TaskDetailsContract.Presenter by inject()

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_task_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(EXTRA_TASK_ID)?.let { taskID = it }
        tryDisplayTask(taskID)
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        tryDisplayTask(taskID)
    }

    private fun tryDisplayTask(taskId: String) {
        try {
            presenter.getTask(taskId)
        } catch (e: NoSuchElementException) {
            context?.displayToast(getString(R.string.noTaskFound))
        }
    }

    fun onTaskUpdateF(title: String, content: String, priority: Int, id: String) {
        val dialog = UpdateTaskFragmentDialog.newInstance()
        dialog.setTaskUpdatedListener(this)
        dialog.setCurrentTask(title, content, priority, id)
        dialog.show(childFragmentManager, dialog.tag)
    }

    override fun onTaskUpdate(title: String, content: String, priority: Int, id: String) {
        detailsTaskTitle.text = title
        detailsTaskDescription.text = content
        detailsPriorityView.setBackgroundResource(
            when (priority) {
                1 -> PriorityColor.LOW.getColor()
                2 -> PriorityColor.MEDIUM.getColor()
                else -> PriorityColor.HIGH.getColor()
            }
        )
        update.setOnClickListener { onTaskUpdateF(title, content, priority, id) }
        this.activity?.onBackPressed()

    }


    override fun onUpdateSuccessful(response: BackendTask) {
        response.run {
            detailsTaskTitle.text = title
            detailsTaskDescription.text = content
            detailsPriorityView.setBackgroundResource(
                when (taskPriority) {
                    1 -> PriorityColor.LOW.getColor()
                    2 -> PriorityColor.MEDIUM.getColor()
                    else -> PriorityColor.HIGH.getColor()
                }
            )
            update.setOnClickListener { onTaskUpdateF(title, content, taskPriority, id) }
        }    }

    override fun onFailure(error: String) {
        Taskie.instance.displayToast(error)
    }


    companion object {
        const val NO_TASK = " "

        fun newInstance(taskId: String): TaskDetailsFragment {
            val bundle = Bundle().apply { putString(EXTRA_TASK_ID, taskId) }
            return TaskDetailsFragment().apply { arguments = bundle }
        }
    }
}
