let appID = "c19535226037438d888116d611ec4fc9";
let uid = String(Math.floor(Math.random() * 232));
let token = null;
let channelName = "mensajes";

let initiateRTM = async () => {
    let client = await AgoraRTM.createInstance(appID);
    await client.login({ uid, token });

    const channel = await client.createChannel(channelName);
    await channel.join();

    let form = document.getElementById("form");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();
        let message = e.target.message.value;
        await channel.sendMessage({ text: message, type: "text" });
        form.reset();

        handleMessage({ text: message });
    });

    channel.on("ChannelMessage", (message, peerId) => {
        console.log("Message:", message);
        handleMessage(message);
    });
};

let handleMessage = async (message) => {
    let messages = document.getElementById("messages");
    let messageElement = `<p>${message.text}</p>`;
    messages.insertAdjacentHTML("beforeend", messageElement);
};

initiateRTM();
