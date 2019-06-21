package hr.ferit.brunozoric.taskie.di

import hr.ferit.brunozoric.taskie.presentation.*
import hr.ferit.brunozoric.taskie.ui.activities.login.LoginContract
import hr.ferit.brunozoric.taskie.ui.activities.register.RegisterContract
import hr.ferit.brunozoric.taskie.ui.fragments.addtask.AddTaskDialogContract
import hr.ferit.brunozoric.taskie.ui.fragments.taskdetails.TaskDetailsContract
import hr.ferit.brunozoric.taskie.ui.fragments.tasks.TasksFragmentContract
import hr.ferit.brunozoric.taskie.ui.fragments.updatetask.UpdateTaskDialogContract
import org.koin.dsl.module

val presenterModule = module {
    factory<LoginContract.Presenter> {LoginPresenter(get())}
    factory<RegisterContract.Presenter> { RegisterPresenter(get()) }
    factory<TasksFragmentContract.Presenter> { TasksFragmentPresenter(get(), get()) }
    factory<AddTaskDialogContract.Presenter> { AddTaskDialogPresenter(get()) }
    factory<TaskDetailsContract.Presenter> { TaskDetailsPresenter(get()) }
    factory<UpdateTaskDialogContract.Presenter> { UpdateDialogPresenter(get()) }
}