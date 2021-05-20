import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

import static com.mayateam.server.ServerUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServerUtilTest {
    @BeforeEach
    public void init() {
    }


    @Order(2)
    @Test
    @DisplayName("createServerSocket returns socket that is bound")
    @SneakyThrows
    void createServerSocketReturnsBoundSocket() {
        try (ServerSocket serverSocket = createServerSocket(8899)) {

            assertThat(serverSocket.isBound()).isTrue();
        }
    }

    @Order(3)
    @Test
    @DisplayName("createServerSocket returns socket that is not closed")
    @SneakyThrows
    void createServerSocketReturnsNotClosedSocket() {
        try (ServerSocket serverSocket = createServerSocket(8899)) {

            assertFalse(serverSocket.isClosed());
        }
    }

    @Order(4)
    @Test
    @SneakyThrows
    @DisplayName("acceptClientSocket accepts request and returns an instance of client socket")
    void acceptClientSocketAcceptsRequest() {
        ServerSocket serverSocketMock = mock(ServerSocket.class);
        Socket clientSocketMock = mock(Socket.class);
        when(serverSocketMock.accept()).thenReturn(clientSocketMock);

        Socket acceptedSocket = acceptClientSocket(serverSocketMock);

        assertThat(acceptedSocket).isEqualTo(clientSocketMock);
    }

    @Order(5)
    @Test
    @SneakyThrows
    @DisplayName("acceptClientSocket does not modify client socket")
    void acceptClientSocketDoesNotModifySocket() {
        ServerSocket serverSocketMock = mock(ServerSocket.class);
        Socket clientSocketMock = mock(Socket.class);
        when(serverSocketMock.accept()).thenReturn(clientSocketMock);

        acceptClientSocket(serverSocketMock);

        verify(serverSocketMock).accept();
        verifyNoInteractions(clientSocketMock);
    }

    @Order(6)
    @Test
    @SneakyThrows
    @DisplayName("acceptClientSocket does not modify server socket")
    void acceptClientSocketDoesNotModifyServerSocket() {
        ServerSocket serverSocketMock = mock(ServerSocket.class);
        Socket clientSocketMock = mock(Socket.class);
        when(serverSocketMock.accept()).thenReturn(clientSocketMock);

        acceptClientSocket(serverSocketMock);

        verify(serverSocketMock).accept();
        verifyNoMoreInteractions(serverSocketMock);
    }

}
