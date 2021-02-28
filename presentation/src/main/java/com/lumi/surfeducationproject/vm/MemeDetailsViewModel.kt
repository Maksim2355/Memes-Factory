package com.lumi.surfeducationproject.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Meme
import com.example.domain.model.User
import com.example.domain.repository.MemeRepository
import com.example.domain.repository.UserRepository
import com.lumi.surfeducationproject.common.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class MemeDetailsViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val memeRepository: MemeRepository
) : ViewModel() {

    init {
        userRepository.getUser().observeOn(AndroidSchedulers.mainThread()).subscribe({
            _user.value = it
        }, {
            //Todo необходима обработка ошибок
            val mockUser = User(
                -1,
                "no data",
                "no data",
                "no data",
                "no data"
            )
            _user.value = mockUser
        })
    }

    private val _meme: MutableLiveData<Meme> = MutableLiveData()
    val meme: LiveData<Meme>
        get() = _meme

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User?>
        get() = _user

    private val _shareMemeEvent: MutableLiveData<Event<Meme>> = MutableLiveData()
    val shareMemeEvent: LiveData<Event<Meme>>
        get() = _shareMemeEvent

    //Тут бул не нужен, навигация плохая, а я Максим
    private val _navigateBackstack: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateBackstack: LiveData<Event<Boolean>>
        get() = _navigateBackstack

    fun navigateBack() {
        _navigateBackstack.value = Event(true)
    }

    fun bindMeme(memeId: Int) {
        memeRepository.getMemes().observeOn(AndroidSchedulers.mainThread()).subscribe({ listMeme ->
            listMeme.find { meme -> meme.id == memeId }?.let { _meme.value = it }
        }, {
            //Какую-то обработку, мб снакбар
        })
    }

    fun shareMeme() {
        _meme.value?.let {
            _shareMemeEvent.value = Event(it)
        }
    }
}
