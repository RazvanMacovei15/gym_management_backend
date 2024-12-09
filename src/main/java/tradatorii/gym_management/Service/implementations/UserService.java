package tradatorii.gym_management.Service.implementations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tradatorii.gym_management.Entity.Task;
import tradatorii.gym_management.Entity.User;
import tradatorii.gym_management.Enums.Role;
import tradatorii.gym_management.Repo.UserRepo;
import tradatorii.gym_management.Service.UserServiceInterface;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserService implements UserServiceInterface {
    private final UserRepo userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllManagers() {
        return userRepository.findAllByRole(Role.MANAGER);
    }
    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
    @Override
    public User update(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    user.setPassword(updatedUser.getPassword());
                    user.setRole(updatedUser.getRole());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }
    @Override
    public Long delete(Long id) {
        userRepository.deleteById(id);
        return id;
    }

    @Override
    public Set<Task> getCreatedTasks(Long userId) {
        if(userRepository.findById(userId).isPresent())
        {
            return userRepository.findById(userId).get().getTasksCreated();
        }
        else
        {
            throw new RuntimeException("User not found with id " + userId);
        }

    }

}


