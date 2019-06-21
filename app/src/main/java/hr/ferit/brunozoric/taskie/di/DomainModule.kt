package hr.ferit.brunozoric.taskie.di

import hr.ferit.brunozoric.taskie.domain.deletetask.DeleteTaskUseCase
import hr.ferit.brunozoric.taskie.domain.deletetask.DeleteTaskUseCaseImpl
import hr.ferit.brunozoric.taskie.domain.edittask.EditTaskUseCase
import hr.ferit.brunozoric.taskie.domain.edittask.EditTaskUseCaseImpl
import hr.ferit.brunozoric.taskie.domain.gettask.GetTaskByIdUseCase
import hr.ferit.brunozoric.taskie.domain.gettask.GetTaskByIdUseCaseImpl
import hr.ferit.brunozoric.taskie.domain.gettasks.GetTasksUseCase
import hr.ferit.brunozoric.taskie.domain.gettasks.GetTasksUseCaseImpl
import hr.ferit.brunozoric.taskie.domain.login.LoginUseCase
import hr.ferit.brunozoric.taskie.domain.login.LoginUseCaseImpl
import hr.ferit.brunozoric.taskie.domain.registration.RegisterUseCase
import hr.ferit.brunozoric.taskie.domain.registration.RegisterUseCaseImpl
import hr.ferit.brunozoric.taskie.domain.savetask.SaveTaskUseCase
import hr.ferit.brunozoric.taskie.domain.savetask.SaveTaskUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<LoginUseCase> { LoginUseCaseImpl(get()) }
    factory<RegisterUseCase> {RegisterUseCaseImpl(get())}
    factory<GetTasksUseCase> { GetTasksUseCaseImpl(get()) }
    factory<GetTaskByIdUseCase> { GetTaskByIdUseCaseImpl(get()) }
    factory<SaveTaskUseCase> { SaveTaskUseCaseImpl(get()) }
    factory<EditTaskUseCase> { EditTaskUseCaseImpl(get()) }
    factory<DeleteTaskUseCase> { DeleteTaskUseCaseImpl(get()) }
}