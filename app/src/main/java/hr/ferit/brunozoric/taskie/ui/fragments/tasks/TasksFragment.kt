package hr.ferit.brunozoric.taskie.ui.fragments.tasks

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.*
import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.DeleteTaskRequest
import hr.ferit.brunozoric.taskie.model.response.DeleteResponse
import hr.ferit.brunozoric.taskie.model.response.GetTasksResponse
import hr.ferit.brunozoric.taskie.presentation.TasksFragmentPresenter
import hr.ferit.brunozoric.taskie.ui.activities.container.ContainerActivity
import hr.ferit.brunozoric.taskie.ui.adapters.TaskAdapter
import hr.ferit.brunozoric.taskie.ui.fragments.addtask.AddTaskFragmentDialog
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tasks.*
import org.koin.android.ext.android.inject

class TasksFragment : BaseFragment(), AddTaskFragmentDialog.TaskAddedListener, TasksFragmentContract.View {

    private val adapter by lazy { TaskAdapter({ onItemSelected(it) }, { onLongClick(it) }) }

    private val presenter: TasksFragmentContract.Presenter by inject()

    companion object {
        fun newInstance(): Fragment {
            return TasksFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        getAllTasks()
    }

    override fun getLayoutResourceId() = R.layout.fragment_tasks

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initListeners()
    }

    private fun initUi() {
        progress.visible()
        noData.visible()
        tasksRecyclerView.layoutManager = LinearLayoutManager(context)
        tasksRecyclerView.adapter = adapter
        getAllTasks()
        swipeRefresh.setOnRefreshListener {
            getAllTasks()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun initListeners() {
        addTask.setOnClickListener { addTask() }
    }

    private fun onLongClick(it: BackendTask): Boolean {
        presenter.onTaskDelete(DeleteTaskRequest(it.id))
        Toast.makeText(this.context, "delete", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onTaskDeleted(response: DeleteResponse) {
        progress.gone()
        getAllTasks()
    }

    override fun onTaskDeletedFailure(error: String) {
        progress.gone()
    }

    private fun onItemSelected(task: BackendTask) {
        val detailsIntent = Intent(context, ContainerActivity::class.java).apply {
            putExtra(EXTRA_SCREEN_TYPE, ContainerActivity.SCREEN_TASK_DETAILS)
            putExtra(EXTRA_TASK_ID, task.id)
        }
        startActivity(detailsIntent)
    }

    override fun onTaskAdded(task: BackendTask) {
        adapter.addData(task)
    }

    private fun addTask() {
        val dialog = AddTaskFragmentDialog.newInstance()
        dialog.setTaskAddedListener(this)
        dialog.show(childFragmentManager, dialog.tag)
    }

    private fun getAllTasks() {
        progress.visible()
        presenter.onGetAllTasks()
    }

    override fun onTaskListReceived(response: GetTasksResponse) {
        response.notes?.run {
            checkList(this)
            adapter.setData(this)
        }
    }

    override fun onGetTasksFailed(error: String) {
        this.activity?.displayToast(error)
    }

    private fun checkList(notes: MutableList<BackendTask>) {
        if (notes.isEmpty()) {
            noData.visible()
        } else {
            noData.gone()
        }
    }
}